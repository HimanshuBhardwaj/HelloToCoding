package dec25;

public class B {
    public static void main(String[] args) {
        EditDistance editDistance = new EditDisImplTopDown();
        System.out.println(editDistance.distance("cabbaffff..","ababba..."));
        System.out.println(new EditDistanceDP().distance("cabbaffff..","ababba..."));

    }
}

class EditDistanceDP implements EditDistance {

    @Override
    public int distance(String a, String b) {
        int dp[][] = new int[a.length()+1][b.length()+1];
        dp[0][0]=0;

        for (int i=1;i<a.length();i++) {
            dp[i][0]=i;
        }

        for (int i=1;i<b.length();i++) {
            dp[0][i]=i;
        }

        for (int i=1;i<=a.length();i++) {
            for (int j=1;j<=b.length();j++) {
                if (a.charAt(i-1) == b.charAt(j-1)) {
                    dp[i][j]=dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                }
            }
        }

        return dp[a.length()][b.length()];
    }
}

class EditDisImplTopDown implements  EditDistance {

    @Override
    public int distance(String a, String b) {
        return distancehelper(a.toCharArray(),a.length()-1,b.toCharArray(),b.length()-1);
    }

    private int distancehelper(char[] a, int al, char[] b, int bl) {
        if (al<0 && bl<0) {
            return 0;
        }
        if (al<0) {
            return bl+1;
        }
        if (bl < 0) {
            return al+1;
        }
        if (a[al]==b[bl]) {
            return distancehelper(a,al-1,b,bl-1);
        }
        return 1+Math.min(Math.min(distancehelper(a,al-1,b,bl),distancehelper(a,al,b,bl-1)),distancehelper(a,al-1,b,bl-1));
    }
}

interface EditDistance {
    int distance(String a, String b);
}