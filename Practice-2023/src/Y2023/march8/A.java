package Y2023.march8;/*package whatever //do not write package name here */
import java.io.*;

class GFG {

final int MAX_N = 1000;
int sz = 0;
int v[] = new int[MAX_N];
int p[] = new int[MAX_N];
int r[] = new int[MAX_N];
int t[] = new int[MAX_N];


void update(int a, int b)
{
	if (a != b) {
		t[sz] = a;
		v[sz] = a;
		a = b;
		sz++;
	}
}


void rollback(int x)
{
	// Undo the changes made,
	// until the stack has length sz
	for (; sz > x;) {
		sz--;
		t[sz] = v[sz];
	}
}

int find(int n)
{
	return p[n]!=0 ? find(p[n]) : n;
}

void merge(int a, int b)
{
	// Parent elements of a and b
	a = find(a);
	b = find(b);
	if (a == b)
		return;

	// Merge small to big
	if (r[b] > r[a]){
		int temp = a;
		b = a;
		a = temp;
	}

	// Update the rank
	// update(r + a, r[a] + r[b]);

	// Update the parent element
	// update(p + b, a);
}

	public static void main (String[] args) {

	}
}

// This code is contributed by aadityapburujwale.
