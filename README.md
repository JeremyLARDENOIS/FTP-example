# FTP client server example

This is a simple example of a FTP client and server.

## Server

### Dependencies

- docker

### Run

```bash
docker compose up -d
```

### Stop

```bash
docker compose down
```

## Client

### Python

#### Dependencies

- python3.9

#### Run

```bash
python3.9 ftp_client.py
```

### Java

For java, I had a problem, I was only able to do it in passive mode, I don't know why.

#### Dependencies

- java 17
- maven 3.9.1

I recommend using [sdkman](https://sdkman.io/) to install java and maven.

#### Run

```bash
cd my-ftp-client
mvn compile exec:java -Dexec.mainClass="com.example.App"
```

## FTPS

To generate a SSL/TLS certificate:

```bash
# Put the information requested or leave it by default
mkdir certs
openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout certs/privatekey.key -out certs/certificate.crt
```

After that, you can run the server like this:

```bash
docker compose -f ftps/docker-compose-ftps.yml up
```

Note that the port are differents.

For the password, check the logs. The username is 'username'.

You can now go in the ftps folder and run the clients like previously.
