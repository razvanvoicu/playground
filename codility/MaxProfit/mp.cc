#include <algorithm>
#include <climits>

using namespace std;

int solution(vector<int> &A) {
    int N = A.size() ;
    long long minPrice = INT_MAX;
    long long maxPrice = INT_MIN;
    long long maxProfit = 0;
    for ( int i = 0 ; i < N ; i++ ) {
    	if ( A[i] < minPrice ) {
    		minPrice = A[i];
    		maxPrice = INT_MIN;
    	} else if ( A[i] > maxPrice ) {
    		maxPrice = A[i];
    	}
    	maxProfit = max(maxProfit,maxPrice-minPrice);
    }
    return maxProfit;
}