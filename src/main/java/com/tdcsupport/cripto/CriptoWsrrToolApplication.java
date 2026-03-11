package com.tdcsupport.cripto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


@SpringBootApplication
public class CriptoWsrrToolApplication implements CommandLineRunner {

    @Autowired
    CryptoService cryptoService;

	public static void main(String[] args) {
		SpringApplication.run(CriptoWsrrToolApplication.class, args);

	}

    @Override
    public void run(String... args) throws Exception {
        Scanner scannerInput = new Scanner(System.in);

        while(true) {
            clearConsole();
            System.out.println("\n\n=======================MENU CRYPTO TDC TOOL=======================");
            System.out.println("1. Cifrar texto con certificado");
            System.out.println("0. Salir");
            System.out.println("Seleccione una opción: ");
            String input = scannerInput.nextLine();

            if(input.equals("0")) {
                System.out.println("Saliendo de la aplicación...");
                break;
            }

            if(input.equals("1")) {
                System.out.println("Ingrese el texto a cifrar:");
                String plainText = scannerInput.nextLine();
                System.out.println("Text Encriptado: ");
                System.out.println(cryptoService.encryptText(plainText));
                scannerInput.nextLine();
            }
        }
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
