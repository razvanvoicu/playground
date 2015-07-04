
int gcd(int a, int b) {
	if (b == 0) return a;
	if (a < b) return gcd(b,a);
	return gcd(b,a%b);
}

int solution(int N, int M) {
	return N/gcd(N,M);
}
