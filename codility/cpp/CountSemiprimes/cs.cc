
vector<int> solution(int N, vector<int> &P, vector<int> &Q) {
	int M = P.size() ;
	vector<int> sieve(N+1,1) ;
	sieve[0] = sieve[1] = 0 ;
	for ( long long i = 2 ; i * i <= N ; i ++) {
		if ( sieve[i] ) {
			for ( int k = i * i ; k <= N ; k += i ) sieve[k] = 0 ; 
		}
	}

	vector<bool> semiPrime(N+1);
	for ( long long i = 0 ; i * i <= N ; i++ )
		if ( sieve[i] )
			for ( long long j = 0 ; j * i <= N ; j++ )
				if ( sieve[j] ) semiPrime[i*j] = true;


	vector<int> semiPrimeCount(N+1);
	int count = 0 ;
	for ( int i = 0 ; i <= N ; i++ ) {
		semiPrimeCount[i] = count ;
		if ( semiPrime[i] ) count ++ ;
	}
	semiPrimeCount[N] = count;

	vector<int> result(M);
	for ( int i = 0 ; i < M ; i++ ) {
		result[i] = semiPrimeCount[Q[i]] - semiPrimeCount[P[i]-1] ;
	}
	return result;
}
