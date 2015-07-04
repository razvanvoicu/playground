
int solution(int N) {
	if ( N == 1 ) return 1 ;
	int count = 2;
	for ( long long i = 2 ; i * i <= N ; i ++ )
		if ( N % i == 0 ) 
			if ( N / i == i ) count += 1 ;
			else count += 2 ;
	return count;
}