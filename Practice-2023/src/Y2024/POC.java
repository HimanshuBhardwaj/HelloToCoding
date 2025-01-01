package Y2024;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Himanshu Bhardwaj
 * @Date 1/15/2024
 */
public class POC {
    public static void main(String[] args) {
        String str = "\\\"84b7b2fe-3a4d-4bfd-b6d8-77f97c856440_b4d79fd1-bd74-4193-b09e-90ee302684f8\\\",\\\"84b7b2fe-3a4d-4bfd-b6d8-77f97c856440_196ee1e6-5124-4916-9b38-62a694182f45\\\",\\\"84b7b2fe-3a4d-4bfd-b6d8-77f97c856440_b4d79fd1-bd74-4193-b09e-90ee302684f8\\\",\\\"84b7b2fe-3a4d-4bfd-b6d8-77f97c856440_764fb974-fa8e-4d06-920f-bb7f65e3976f\\\",\\\"84b7b2fe-3a4d-4bfd-b6d8-77f97c856440_b9c0a490-b71c-4eda-9635-8c6fb297f822\\\",\\\"84b7b2fe-3a4d-4bfd-b6d8-77f97c856440_61b31c6b-378b-4242-a060-ec6252e62471\\\",\\\"84b7b2fe-3a4d-4bfd-b6d8-77f97c856440_a2a53fc0-f915-469f-b481-f8c88db8c9d8\\\",\\\"84b7b2fe-3a4d-4bfd-b6d8-77f97c856440_4e6dcf7d-752d-4633-8bb3-d9625fa4a42c\\\",\\\"84b7b2fe-3a4d-4bfd-b6d8-77f97c856440_50da841f-7648-489a-9c82-adfb5558ec3e\\\",\\\"84b7b2fe-3a4d-4bfd-b6d8-77f97c856440_55f5f4ad-f1f4-4eca-8e9a-c59f14e265c5\\\",\\\"84b7b2fe-3a4d-4bfd-b6d8-77f97c856440_65757ad3-eaeb-4855-b612-82e86562079a\\\"";
        String as[] = str.split(",");
        System.out.println(as.length);

        HashSet<String> hashSet = new HashSet<>();

        for (String a:as) {
            //System.out.println(a);
            if (hashSet.contains(a)) {
                System.out.println(a);
            }
            hashSet.add(a);
        }


    }
}
