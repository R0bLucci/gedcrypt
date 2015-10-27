import hashlib
import os

from Crypto.Cipher import AES
from Crypto.Util import Counter

class HashMismatch(Exception):
    pass

def encrypt_file(passwd, filename):
	assert isinstance(passwd, bytes)
	assert os.path.isfile(filename)
	with open(filename, 'rb') as plaintext_file, open(filename + '.tmp', 'wb') as ciphertext_file:
		# derive AES key from password and salt
		salt = os.urandom(32)
		aes_key = hashlib.pbkdf2_hmac('sha512', passwd, salt, 200000, dklen=32)

		# save salt used to derive AES key and hash of AES key (see decrypt_file())
		ciphertext_file.write(salt + hashlib.sha512(aes_key).digest())

		# encrypt file
		aes_cipher = AES.new(aes_key, mode=AES.MODE_CTR, counter=Counter.new(128))

		while True:
			# encrypt plaintext 10 MiB at a time
			plaintext = plaintext_file.read(10485760)

			if len(plaintext) == 0:
				break

			ciphertext_file.write(aes_cipher.encrypt(plaintext))

	os.rename(filename + '.tmp', filename)

def decrypt_file(passwd, filename):
	assert isinstance(passwd, bytes)
	assert os.path.isfile(filename)

	with open(filename, 'rb') as ciphertext_file, open(filename + '.tmp', 'wb') as plaintext_file:
		# derive AES key from password and salt
		salt = ciphertext_file.read(32)
		aes_key = hashlib.pbkdf2_hmac('sha512', passwd, salt, 200000, dklen=32)

		# verify derived AES key is the same as the one used to encrypt the file
		if hashlib.sha512(aes_key).digest() == ciphertext_file.read(64):
			# decrypt file
			aes_cipher = AES.new(aes_key, mode=AES.MODE_CTR, counter=Counter.new(128))

			while True:
				# decrypt ciphertext 10 MiB at a time
				ciphertext = ciphertext_file.read(10485760)

				if len(ciphertext) == 0:
					break

				plaintext_file.write(aes_cipher.decrypt(ciphertext))
		else:
			os.remove(filename + '.tmp')
			raise HashMismatch('\nThe decryption AES key hash does not match the one in the encrypted file.\nPossible causes: 0. file is not encrypted 1. password is incorrect.')

	os.rename(filename + '.tmp', filename)
