int solution(int A, int B, int K) {
	int q = (B-A) / K ;
	if ( B % K == 0 ) return q+1;
	else return q;
}