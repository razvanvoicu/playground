int solution(string &S) {
	int N = S.size();
	if ( N % 2 == 0 ) return -1;
	bool isPal = true ;
	for ( int i = 0 ; i < N/2 ; i++ )
		if ( S[i] != S[N-i-1] ) {
			isPal = false ;
			break ;
		}
	if (isPal) return N/2;
	return -1;
}