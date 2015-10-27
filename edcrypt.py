import os
import sys

import crypto

def fail_exit():
	print('Usage: python edcrypt.py -e(encrypt)/-d(decrypt) filename password')
	raise SystemExit(1)

# check command line args are valid
if len(sys.argv) != 4:
	print("error")
	fail_exit()

if not os.path.isfile(sys.argv[2]):
	print('File ' + sys.argv[2] + ' does not exist.')
	raise SystemExit(1)

# parse command line args
if sys.argv[1] == '-e':
	print(sys.argv[0])
	print(sys.argv[1])
	print(sys.argv[2])
	print(sys.argv[3])
	print("------------------------------------------------")
	crypto.encrypt_file(sys.argv[3].encode(), sys.argv[2])
elif sys.argv[1] == '-d':
	print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
	crypto.decrypt_file(sys.argv[3].encode(), sys.argv[2])
else:
	print(sys.argv[1])
	print(sys.argv[3])
	print(sys.argv[2])
	fail_exit()
