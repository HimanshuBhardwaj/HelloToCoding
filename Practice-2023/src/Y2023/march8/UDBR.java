package Y2023.march8;

/**
 * @author Himanshu Bhardwaj
 * @Date 3/8/2024
 */
public class UDBR {
    public static void main(String[] args) {
        UnionDIn unionDIn = new UnionByRank(5);
//        unionDIn.union(0,1);
//        unionDIn.union(2,1);
//        unionDIn.union(2,0);
//        unionDIn.union(3,2);
//        unionDIn.union(3,1);
//        unionDIn.union(3,0);
//        unionDIn.union(4,1);
        System.out.println(unionDIn.numComponent());

        UnionDIn unionDIns = new UnionBySize(5);
//        unionDIns.union(0,1);
//        unionDIns.union(2,1);
//        unionDIns.union(2,0);
//        unionDIns.union(3,2);
//        unionDIns.union(3,1);
//        unionDIns.union(3,0);
//        unionDIns.union(4,1);
        System.out.println(unionDIns.numComponent());


    }
}

class UnionBySize implements UnionDIn {
    int [] parent;
    int n;
    int size[];

    int numComponent;

    public UnionBySize(int n) {
        this.n = n;
        this.parent = new int[n];
        this.size = new int[n];
        this.numComponent = n;

        for (int i=0;i<n;i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    @Override
    public void union(int s, int d) {
        int rs = getRoot(s);
        int rd = getRoot(d);

        if (rs == rd) {
            return;
        }

        if (size[rd] > size[rs]) {
            parent[rd] = rs;
            size[rd] += size[rs];
        } else if (size[rd] > size[rs]) {
            parent[rs] = rd;
            size[rs] += size[rd];
        } else {
            parent[rs] = rd;
            size[rs] += size[rd];
        }
        numComponent--;
    }

    int getRoot(int x) {
        if (x == parent[x]) {
            return x;
        }

        return getRoot(parent[x]);
    }

    @Override
    public int numComponent() {
        return numComponent;
    }
}


interface UnionDIn {
    void union(int s, int d);

    int numComponent();
}


class UnionByRank implements UnionDIn {
    int [] parent;
    int [] rank; // height

    int n;

    int numComponent;

    public UnionByRank(int n) {
        this.n = n;
        parent = new int[n];
        rank = new int[n];
        this.numComponent = n;

        for (int i=0;i<n;i++) {
            parent[i]=i;
        }
    }

    @Override
    public void union(int s, int d) {
        int ps = parent(s);
        int pd = parent(d);

        if (ps == pd) {
            return;
        }

        if (rank[ps] > rank[pd]) {
            parent[pd] = ps;
        } else if (rank[ps] < rank[pd]) {
            parent[ps] = pd;
        } else {
            parent[ps] = pd;
            rank[pd]++;
        }
        numComponent--;
    }

    int parent (int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent(parent[x]);
    }

    @Override
    public int numComponent() {
        return numComponent;
    }
}
