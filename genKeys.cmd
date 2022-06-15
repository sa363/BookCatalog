REM ssh-keygen -t rsa -P "" -b 4096 -m PEM -f jwtRS256.key
REM ssh-keygen -e -m PEM -f jwtRS256.key > jwtRS256.key.pub

"C:\Program Files\Git\usr\bin\openssl.exe" genrsa -out private.pem 2048
"C:\Program Files\Git\usr\bin\openssl.exe" rsa -in private.pem -outform PEM -pubout -out public.pem

rem keytool -genkeypair -alias mytest -keyalg RSA -keypass mypass -keystore mytest.jks -storepass mypass