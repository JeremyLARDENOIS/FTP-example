package com.example;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class App {

    public static void main(String[] args) {
        String server = "localhost";
        int port = 21;
        String user = "user1";
        String pass = "pass1";

        FTPClient ftpClient = new FTPClient();
        try {
            // Se connecter au serveur FTP
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);

            // Utiliser le mode passif
            ftpClient.enterLocalPassiveMode();

            System.out.println("Current working directory: " + ftpClient.printWorkingDirectory());

            // Créer un fichier test.txt et le télécharger sur le serveur FTP
            String localFilePath = "test.txt";
            Files.write(Paths.get(localFilePath), "Ceci est un fichier test.".getBytes(StandardCharsets.UTF_8));

            FileInputStream inputStream = new FileInputStream(new File(localFilePath));
            if (ftpClient.storeFile("test.txt",  inputStream)) {
                System.out.println("Fichier téléchargé avec succès");
            } else {
                System.out.println("Erreur lors du téléchargement du fichier");
                System.out.println("Code de réponse du serveur: " + ftpClient.getReplyCode());
                System.out.println("Message de réponse du serveur: " + ftpClient.getReplyString());
            }
            inputStream.close();

            // Afficher le contenu du répertoire courant
            System.out.println("Liste des fichiers du répertoire courant:");
            FTPFile[] files = ftpClient.listDirectories();
            for (FTPFile file : files) {
                String details = file.getName();
                if (file.isDirectory()) {
                    details = "[" + details + "]";
                }
                System.out.println(details);
            }

            // Télécharger le fichier test.txt du serveur FTP
            System.out.println("Téléchargement du fichier test.txt");
            String remoteFilePath = "test.txt";
            String downloadedFilePath = "downloaded_test.txt";
            FileOutputStream outputStream = new FileOutputStream(downloadedFilePath);
            ftpClient.retrieveFile(remoteFilePath, outputStream);
            outputStream.flush();
            outputStream.close();

            // Se déconnecter et fermer la connexion FTP
            System.out.println("Déconnexion du serveur FTP");
            ftpClient.logout();
            ftpClient.disconnect();
            System.out.println("Connexion fermée");

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
