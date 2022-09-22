@ECHO OFF
ECHO Building docker image
docker build -t fibonacci .
ECHO Deploy image to docker 
docker run -d -p 8080:8080 fibonacci
ECHO Success
pause