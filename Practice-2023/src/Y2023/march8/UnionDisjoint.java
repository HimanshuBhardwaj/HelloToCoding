package Y2023.march8;

/**
 * @author Himanshu Bhardwaj
 * @Date 3/8/2024
 */
public class UnionDisjoint {
    public static void main(String[] args) {
        UnionDI unionDI = new UnionD(6);
        unionDI.union(0,1);
        unionDI.union(2,1);
        System.out.println(unionDI.numComponent());

        unionDI.union(3,4);
        unionDI.union(4,5);
        System.out.println(unionDI.numComponent());
        unionDI.union(3,5);
        System.out.println(unionDI.numComponent());
        unionDI.union(2,5);
        System.out.println(unionDI.numComponent());
    }
}

class UnionD implements UnionDI {
    int n;
    int [] parent;

    int numComponents;

    public UnionD(int n) {
        this.n = n;
        parent = new int[n];

        for (int i=0;i<n;i++) {
            parent[i]=i;
        }

        this.numComponents = n;
    }

    @Override
    public void union(int s, int d) {
        if (s > d) {
            union(d,s);
        }

        if (s==d) {
            return;
        }

        int ss = getSet(s);
        int sd = getSet(d);

        if (ss == sd) {
            return;
        }

        parent[ss] = parent[sd];
        numComponents--;
    }

    int getSet(int x) {
        if (x == parent[x]) {
            return x;
        }
        parent[x] = getSet(parent[x]);
        return parent[x];
    }

    @Override
    public int numComponent() {
        return numComponents;
    }
}

interface UnionDI {
    void union(int s, int d);

    int numComponent();
}
