#include <algorithm>

int solution(int N) {
	bool oneEncountered = false;
	int count = 0;
	int m = 0;
	for ( ; N != 0 ; N >>= 1 ) {
		int bit = N & 1 ;
		if ( bit ) { 
			oneEncountered = true ;
			count = 0 ;
		}
		if ( oneEncountered && !bit ) {
			count ++ ;
			m = max(m,count);
		}
	}
	return m ;
}