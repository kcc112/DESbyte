
public class DES {

    private int[] IP = {58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36,
            28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32,
            24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19,
            11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7};

    private int[] invIP = {40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47,
            15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13,
            53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51,
            19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17,
            57, 25};

    private int[] P = {16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5,
            18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25};

    public int[] PC1 = {57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51,
            43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4};

    private int[] PC2 = {14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29,
            32};

    private  int[] keyShift = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2,
            2, 1 };

    private int[] E = {32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8,
            9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32,
            1};;

    private int sboxes[][][] = {
            {
                    {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                    {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                    {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                    {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
            },
            {
                    {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                    {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                    {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                    {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
            },
            {
                    {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                    {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                    {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                    {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
            },
            {
                    {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                    {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                    {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                    {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
            },
            {
                    {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                    {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                    {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                    {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
            },
            {
                    {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                    {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                    {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                    {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
            },
            {
                    {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                    {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                    {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                    {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
            },
            {
                    {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                    {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                    {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                    {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
            }
    };

    //Metoda przyjmuje klucz i wiadomość do zaszyfrowania w postaci tablicy byte[] zwraca zaszyfrowany text w postaci byte[]
    public  byte[] encrypt(byte[] data, byte[] key){

        byte[][] subKeys = generateSubKeys(key);//Generowanie podkluczy
        byte[] compleateText = completeMessage(data);//Dodani do wiadomości odpowiedniej liczby byte aby możliwy był podział na bloki 64 bit
        int length = compleateText.length;
        byte[] output = new byte[compleateText.length];//Rezerwacja pamięci na tablice zawierającą zaszyfrowany txt w postaci byte[]
        byte[] block = new byte[8];
        for(int i = 0; i < length; i=i+8 ){//Podział rozszerzonego tekstu wejściowego  na bloki  po 8 byte (64bit)
            for(int j = 0; j < 8; j++){
                block[j] = compleateText[i+j];
            }

            block = permutFunc(block, IP);//Permutacja IP wykonywana na 64 bitowym bloku
            byte[] L = extractBits(block, 0, 32);//Podział tablicy byte na prawą i lewą stronę , wynik tablica byte[4]
            byte[] R = extractBits(block, 32, 32);

            for (int j = 0; j < 16; j++) {
                byte[] tmpR = R;
                R = fFeistel(R,subKeys[j]);//Wykonanie funkcji Feistela
                R = xor_func(L, R);//Xorowanie prawej i lewej części
                L = tmpR;
            }

            block = connectBits(R, 32, L, 32);//Połączenie prawej i lewej tablicy byte w jedną część
            block = permutFunc(block, invIP);//Wykonanie permutacji odwrotne IP na pojedynczym bloku
            System.arraycopy(block, 0, output, i, block.length);//Kopiowanie bloku do tablicy wyjściowej
        }
        return output;
    }

    public  byte[] decrypt(byte[] data, byte[] key) {

        byte[][] subKeys = generateSubKeys(key);//Generowanie podkluczy
        int length = data.length;
        byte[] output = new byte[length];//Rezerwacja pamięci na tablice zawierającą odszyfrowany  txt w postaci byte[]
        byte[] block = new byte[8];
        for(int i = 0; i < length; i=i+8 ){//Podział rozszerzonego tekstu wejściowego  na bloki  po 8 byte (64bit)
            for(int j = 0; j < 8; j++){
                block[j] = data[i+j];
            }

            block = permutFunc(block, IP);//Permutacja IP wykonywana na 64 bitowym bloku
            byte[] L = extractBits(block, 0, 32);//Podział tablicy byte na prawą i lewą stronę , wynik tablica byte[4]
            byte[] R = extractBits(block, 32, 32);

            for (int j = 0; j < 16; j++) {
                byte[] tmpR = R;
                R = fFeistel(R, subKeys[15-j]);//Wykonanie funkcji Feistela z kluczami wprowadzanymi w odwrotnej kolejności
                R = xor_func(L, R);//Xorowanie prawej i lewej części
                L = tmpR;
            }

            block = connectBits(R, 32, L, 32);//Połączenie prawej i lewej tablicy byte w jedną część
            block = permutFunc(block, invIP);;//Wykonanie permutacji odwrotne IP na pojedynczym bloku
            System.arraycopy(block, 0, output, i, block.length);//Kopiowanie bloku do tablicy wyjściowej
        }
        output = deleteAddedSigns(output);
        return output;
    }

    //Usuwanie byte dodanych  w procesie rozszerzania wiadomości
    private byte[] deleteAddedSigns(byte[] array) {
        int count = 0;//Licznik dodanych zer
        int i = array.length - 1;//-1 aby rozmiar array się zgadzał
        while (array[i] == 0) {
            count++;
            i--;
        }
        byte[] output = new byte[array.length - count - 1];//-1 bo trzeba zlikwidować znak rozpoczynający rozszerzenie 01
        System.arraycopy(array, 0, output, 0, output.length);//kopiowanie do output odpowiedniej liczby byte
        return output;
    }

    //Uzupełnianie wiadomości o odpowiednią liczbę byte aby możliwy był podział na bloki 64 bit (8 byte)
    private byte[] completeMessage(byte[] data){

        //Wyliczenie ile byte trzeba dodać do aktualnej wiadomości aby możliwy był podział na bloki 8 po byte
        int messageLength = data.length;
        int lenght = 8 - messageLength % 8;//ilość byte do dodania do wiadomości
        if(lenght == 8) lenght = 0;
        byte[] output = new byte[messageLength+lenght];

        for(int i=0; i<messageLength; i++){
            output[i]=data[i];//Kopiowanie wszystkich możliwych byte z tablicy wejściowej do wyjściowej
            if(messageLength < 8){//Jeżeli wiadomość miała mniej niż 8 byte dodaj wybrany znak byle nie 0 oraz uzupełniaj zerami dopóki nie będzie 8 byte
                for(int j = messageLength; j < 8; j ++) {
                    if(j == messageLength) output[j] = (byte) 0x1;//dodany znak jako pierwszy byte rozszerzenia
                    else output[j] = 0;
                }
            }
            else if(messageLength > 8){
                int cout = messageLength;
                while((cout)%8 != 0){//Jeżeli wiadomość miała  mniej niż 8 byte dodaj wybrany znak byle nie 0 oraz uzupełniaj zerami dopóki nie będzie x%8=0
                    if(cout == messageLength)output[cout] = (byte) 0x1;//dodany znak jako pierwszy byte rozszerzenia
                    else output[cout] = 0;
                    cout++;
                }
            }
        }
        return output;
    }

    //Xorowanie każdego byte z tablicy
    private  byte[] xor_func(byte[] a, byte[] b) {
        int length = a.length;
        byte[] output = new byte[length];
        for (int i = 0; i < length; i++) {
            output[i] = (byte)(a[i]^b[i]);
        }
        return output;
    }

    //Funkcja Feistela zwraca byte[4]
    private  byte[] fFeistel(byte[] R, byte[] subKey) {
        byte[] output;
        output = permutFunc(R, E);//Permutacja E rozszerzająca do 48 bit
        output = xor_func(output, subKey);//Wykonanie xor na jednym z podkluczy i rozszerzonej prawej części
        output = sBoxes(output);//Wykonanie operacji na za pomocą tablicy sBox output  32 bit 4 byte
        output = permutFunc(output, P);//Permutacja  P na 4 byte bloku
        return output;
    }

    //Wyznacz wartość z tablicy sbox i zapisuje w nowej tablicy byte
    private  byte[] sBoxes(byte[] input) {
        input = separateBytes(input, 6);//Zamienia tablice byut[6] na byte[8]
        byte[] output = new byte[4];
        int halfByte = 0;
        for (int i = 0; i < 8; i++) {
            byte valByte = input[i];
            int arrayRow = 2 * (valByte >> 7 & 0x0001) + (valByte >> 2 & 0x0001);//wyliczanie nr rzędu
            int arrayCol = valByte >> 3 & 0x000F;//wyliczanie nr kolumny
            int val = sboxes[i][arrayRow][arrayCol];//Wyciąganie wartości z tablicy sbox zapisanej na 4 bit
            if (i % 2 == 0) halfByte = val;
            else output[i / 2] = (byte) (16 * halfByte + val);
        }
        return output;
    }

    //Zapisuje w każdym z 8 byte  po 6 bitów
    private byte[] separateBytes(byte[] input, int length) {
        byte[] output = new byte[8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < length; j++) {
                int bit = extractBit(input, length * i + j);//Wyciąga wartość bitów od 0 do 47
                setBit(output, 8 * i + j, bit);//Ustawia bity w tablicy `byte po 6 z lewej 2 z prawej są zerami
            }
        }
        return output;
    }

    //Generowanie 16 podkluczy
    public  byte[][] generateSubKeys(byte[] key){

        byte[] tempKey = new byte[8];//Rezerwacja pamięci na klucz 64 bitowy
        int length = key.length;
        if(length > 8){//Klucz musi być 64 bitowy jeśli ma więcej wypisze błąd
            System.out.println("Klucz ma więcej niż 64 bity");
            System.exit(1);
        }

        for(int i = 0; i<length; i++) tempKey[i] = key[i];//Jeśli klucz ma mniej niż 8 byte dopisujemy dodatkowe byte aby było 8
        while(length <= 7 ){
            tempKey[length] = 0;
            length ++;
        }

        byte[][] output = new byte[16][];//Rezerwacja pamięci na 16 podkluczy
        byte[] permKey = permutFunc(tempKey, PC1);//Wykonanie permutacji PC1 która zmniejszy klucz z 64 bit do 56 byte[7]
        byte[] L = extractBits(permKey, 0, 28);//Podział tablicy byte na prawą i lewą stronę , wynik tablica byte[4]
        byte[] R = extractBits(permKey, 28, 28);

        for (int i = 0; i < 16; i++) {
            L = shiftLeft(L, keyShift[i]);//Przesuwanie bitów o zadaną wartość w lewo
            R = shiftLeft(R, keyShift[i]);

            byte[] connect = connectBits(L, 28, R, 28);//Połączenie prawej i lewej strony
            output[i] = permutFunc(connect, PC2);//Wykonanie Wykonanie permutacji PC2
        }
        return output;
    }

    private  byte[] permutFunc(byte[] input, int[] array) {
        int nrBytes = array.length / 8;//wyliczanie rozmiaru tablicy wyjściowej
        byte[] output = new byte[nrBytes];
        for (int i = 0; i < array.length; i++) {
            int bit = extractBit(input, array[i] - 1);//Wyciąga wartość bitów z podanej tablicy byte i z danej pozycji w niej
            setBit(output, i, bit);//Ustawianie bitu na odpowiednim miejscu w tablicy wyjściowej
        }
        return output;
    }

    //Sprawdz wartość bitu z tablicy byte o podanej pozycji
    private  int extractBit(byte[] imput, int position) {
        int positionByte = position / 8;//w jakim byte się znajduje
        int positionBit = position % 8;//na jakiej pozycji w byte się znajduje
        byte tmpByte = imput[positionByte];
        int bit = ( tmpByte >> (8 - (positionBit + 1))) & 0x0001;//wyliczanie czy 0 czy 1
        return bit;
    }

    //Ustawia bit na podanej pozycji w tablicy byte
    private  void setBit(byte[] data, int position, int bit) {
        int positionByte = position / 8;//w jakim byte ma się znajdujdować
        int positionBit = position % 8;//na jakiej pozycji w byte ma się znajdować
        byte tmpByte = data[positionByte];
        tmpByte = (byte) (((0xFF7F >> positionBit) & tmpByte) & 0x00FF);
        byte newByte = (byte) ((bit << (8 - (positionBit + 1))) | tmpByte);
        data[positionByte] = newByte;
    }

    //Dieli Na P i L
    private  byte[] extractBits(byte[] input, int positionL, int positionR) {
        byte[] output = new byte[4];
        for (int i = 0; i < positionR; i++) {
            int bit = extractBit(input, positionL + i);
            setBit(output, i, bit);
        }
        return output;
    }

    //Przesuwa każdy bit w lewo o ustaloną wartość
    private  byte[] shiftLeft(byte[] input, int shift) {
        byte[] output = new byte[4];
        for (int i = 0; i < 28; i++) {
            int bit = extractBit(input, (i + shift) % 28);
            setBit(output, i, bit);
        }
        return output;
    }

    //Łączenie prawej i lewej strony w jedną tablice byte[]
    private  byte[] connectBits(byte[] r, int rLen, byte[] l, int lLen) {
        int numOfBytes = (rLen + lLen)/8;
        byte[] output = new byte[numOfBytes];
        int j = 0;
        for (int i = 0; i < rLen; i++) {
            int bit = extractBit(r, i);
            setBit(output, j, bit);
            j++;
        }
        for (int i = 0; i < lLen; i++) {
            int val = extractBit(l, i);
            setBit(output, j, val);
            j++;
        }
        return output;
    }
}
