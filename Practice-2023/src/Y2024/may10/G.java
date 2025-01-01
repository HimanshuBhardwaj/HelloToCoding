package Y2024.may10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Himanshu Bhardwaj
 * @Date 5/10/2024
 */
public class G {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            String [] str = br.readLine().split(" ");
            ArrayList<Element> elements = new ArrayList<>(str.length);

            for (int i=0;i<str.length;i++) {
                elements.add(new Element(i,Long.parseLong(str[i])));
            }
            Collections.shuffle(elements);
            Collections.sort(elements);
            for (Element e: elements) {
                pw.append(e.num+" ");
            }
            pw.append("\n");
        }
        pw.flush();
        pw.close();
    }
}

class Element implements Comparable<Element> {
    int index;
    long num;

    public Element(int index, long num) {
        this.index = index;
        this.num = num;
    }

    @Override
    public int compareTo(Element o) {
        System.out.println(this.num+"\t"+o.num+"\t"+(this.num^o.num));
        if ((this.num^o.num) < 4l) {
            return Long.compare(this.num, o.num);
        } else {
            return Long.compare(this.index, o.index);
        }
    }
}
