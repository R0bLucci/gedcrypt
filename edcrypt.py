import crypto
import os
import sys

def fail_exit():
	print('Usage: python edcrypt.py -e(encrypt)/-d(decrypt) filename password')
	raise SystemExit(1)

if len(sys.argv) != 4 or not os.path.isfile(sys.argv[2]):
	fail_exit()

if sys.argv[1] == '-e':
	crypto.encrypt_file(sys.argv[3].encode(), sys.argv[2])
elif sys.argv[1] == '-d':
	crypto.decrypt_file(sys.argv[3].encode(), sys.argv[2])
else:
	fail_exit()
