import java.io.*;
import java.util.*;
import java.util.Map.Entry;

    public class AddressBook {

        private static final String filepath = "contactos.txt";

        public static void main(String[] args) {
            HashMap<String, Object> myMapAddressBook = new HashMap<>();

            Scanner scanner = new Scanner(System.in);
            boolean exit = false;
            int opcion;
            load(myMapAddressBook);
            while (!exit) {
                System.out.println("Selecciona una de las siguentes opciones!" +"\n");
                System.out.println("1. Ver Agenda de Contacto(s)");
                System.out.println("2. Crear un Nuevo Contacto");
                System.out.println("3. Eliminar un Contacto Existente");
                System.out.println("4. Salir del Programa");

                try {
                    System.out.println("Opcion: ");
                    opcion = scanner.nextInt();

                    switch (opcion) {
                        case 1:
                            list(myMapAddressBook);
                            break;
                        case 2:
                            create(myMapAddressBook);
                            break;
                        case 3:
                            delete(myMapAddressBook);
                            break;
                        case 4:
                            save(myMapAddressBook);
                            exit = true;
                            break;
                        default:
                            System.out.println("Por favor ingresa opciones del 1->4!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Para utilizar el programa, debes seleccionar una opcion valida!");
                    scanner.next();
                }
            }
        }

        public static void delete(HashMap myMapAddressBook) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String phone = null;
            System.out.println("Ingrese Teléfono:");

            try {
                phone = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            myMapAddressBook.remove(phone);
            System.out.println("El Contacto a sido Eliminado!" + "\n");
        }

        public static void create(HashMap myMapAddressBook) {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String phone = null;
            String name = null;
            System.out.println("Ingrese Teléfono:");

            try {
                phone = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Ingrese Nombre:");
            try {
                name = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (phone != null && name != null)
                myMapAddressBook.put(phone, name);

            System.out.println("El Contacto a sido Creado!" + "\n");
        }


        public static void list(HashMap myMapAddressBook) {
            System.out.println("\n AGENDA DE CONTACTOS");
            for (Iterator<Entry<String, Object>> entries = myMapAddressBook.entrySet().iterator(); entries.hasNext(); ) {
                Map.Entry<String, Object> entry = entries.next();
                String output = String.format("%s:%s", entry.getKey(), entry.getValue());
                System.out.println(output);
            }
        }

        public static void save(HashMap myMapAddressBook) {
            BufferedWriter bufferedWriter = null;
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(filepath));
                for (Object o : myMapAddressBook.entrySet()) {
                    Entry<String, Object> entry = (Entry<String, Object>) o;
                    String output = String.format("%s,%s", entry.getKey(), entry.getValue() + "\r\n");
                    bufferedWriter.write(output);
                }
            } catch (IOException e) {
                System.out.println("IOException error! (Abrir1)" + e.getMessage());
            } finally {
                try {
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (IOException e) {
                    System.out.println("IOException error! (Cerrar)" + e.getMessage());
                }
            }
        }

        public static void load(HashMap myMapAddressBook) {
            BufferedReader bufferedReader = null;
            String Number = "";
            String Name = "";
            try {
                bufferedReader = new BufferedReader(new FileReader(filepath));

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    int coma = line.indexOf(',');
                    Number = line.substring(0, coma);
                    Name = line.substring(coma + 1, line.length());
                    myMapAddressBook.put(Number, Name);
                }
            } catch (IOException e) {
                System.out.println("No Existe un Archivo 'contactos.txt' - se a creado un Archivo Nuevo!");
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    System.out.println("IOException error! (Cerrar) " + e.getMessage());
                }
            }
        }
    }