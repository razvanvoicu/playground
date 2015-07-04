#include <climits>
#include <algorithm>
#include <iostream>

using namespace std;

int solution(vector<int> &A) {
    int N = A.size();
    int maxNeg = INT_MIN;
    int maxPos = 0 ;
    int sum = 0 ;
    for ( int i = 0 ; i < N ; i ++ ) {
    	maxNeg = max(maxNeg,A[i]) ;
    	sum += A[i] ;
   		maxPos = max(maxPos,sum) ;
    	if ( sum < 0 ) {
    		sum = 0 ;
    	}
    }
    maxPos = max(maxPos,sum) ;
    if ( maxNeg < 0 ) return maxNeg ;
    return maxPos ;
}

int main() {
	vector<int> A ;
	cout << solution(A) << endl ;
	vector<int> B({1}) ;
	cout << solution(B) << endl ;
	vector<int> C({1<<31});
	cout << solution(C) << endl ;
	vector<int> D({0b01111111111111111111111111111111});
	cout << solution(D) << endl ;
	vector<int> E({-1,0,-1,-2}) ;
	cout << solution(E) << endl ;
	vector<int> F({-1,0,-1,3,4,5,-2}) ;
	cout << solution(F) << endl ;
	vector<int> G({-1,0,-1,3,4,5,-2,10,20}) ;
	cout << solution(G) << endl ;
	vector<int> H({-1,20,-100,3,4,5,-2,10,20}) ;
	cout << solution(G) << endl ;
	vector<int> I({-1,-20,-100,-3,-4,-5,-2,-10,-20}) ;
	cout << solution(I) << endl ;
	return 0;
}