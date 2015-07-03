int solution(int A, int B, int K) {
	B = B - B % K;
	int q = (B-A) / K ;
	if ( B < A ) return 0;
	return q+1;
}