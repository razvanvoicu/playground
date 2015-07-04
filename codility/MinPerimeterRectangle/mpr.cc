#include <climits>
#include <algorithm>

int solution(int N) {
	int minPer = INT_MAX;
	for ( long long i = 1 ; i * i <= N ; i++ ) {
		if ( N % i == 0 ) {
			long long per = 2*(i+N/i)) ;
			if (per < minPer) minPer = per;
		}
	}
	return minPer;
}