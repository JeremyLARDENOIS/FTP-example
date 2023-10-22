import ftplib
import getpass

# Demander le mot de passe de l'utilisateur
password = getpass.getpass('Mot de passe : ')

# Se connecter au serveur FTPS local sur le port 2121
ftp = ftplib.FTP_TLS()
ftp.connect('localhost', 4567)
ftp.login('username', password)

# Il est recommandé d'ajouter la ligne suivante pour sécuriser la session avec TLS
# ftp.prot_p()

print(ftp.pwd())

# Créer un fichier test.txt et le télécharger sur le serveur FTPS
with open('test.txt', 'w') as f:
    f.write('Ceci est un fichier test.')

with open('test.txt', 'rb') as f:
    ftp.storbinary('STOR test.txt', f)

# Liste des fichiers du serveur FTPS
print(ftp.nlst())

# Télécharger le fichier test.txt du serveur FTPS
with open('downloaded_test.txt', 'wb') as f:
    ftp.retrbinary('RETR test.txt', f.write)

# Supprimer le fichier test.txt du serveur FTPS
# ftp.delete('test.txt')

ftp.quit()
