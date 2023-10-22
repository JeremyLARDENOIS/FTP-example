import ftplib

# Se connecter au serveur FTP local sur le port 2121
ftp = ftplib.FTP()
ftp.connect('localhost', 2121)
ftp.login('user1', 'pass1')

print(ftp.pwd())

# Créer un fichier test.txt et le télécharger sur le serveur FTP
with open('test.txt', 'w') as f:
    f.write('Ceci est un fichier test.')

with open('test.txt', 'rb') as f:
    ftp.storbinary('STOR test.txt', f)

# Liste des fichiers du serveur FTP
print(ftp.nlst())

# Télécharger le fichier test.txt du serveur FTP
with open('downloaded_test.txt', 'wb') as f:
    ftp.retrbinary('RETR test.txt', f.write)

# Supprimer le fichier test.txt du serveur FTP
# ftp.delete('test.txt')

ftp.quit()