#include <algorithm>

using namespace std;

int solution(vector<int>& A) {
	int N = A.size();
	vector< pair<long long,int> > intersections(2*N);
	for ( long long i = 0 ; i < N ; i ++ ) {
		intersections[2*i] = make_pair(i-A[i],-1) ;
		intersections[2*i+1] = make_pair(i+A[i],1) ;
	}

	sort(intersections.begin(),intersections.end());

	long long open = 0;
	long long n_int = 0 ;
	for ( int i = 0 ; i < 2*N ; i++ ) {
		if ( intersections[i].second < 0 ) {
			n_int += open;
			if (n_int > 10000000L) return -1 ;
			open ++ ;
		} else {
			open -- ;
		}
	}
	return n_int ;
}