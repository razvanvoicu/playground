#include <set>

int solution(vector<int> &A) {
    multiset<int> m;
    int N = A.size();
    for ( int i = 0 ; i < N ; i++ ) {
        m.insert(A[i]);
    }
    for ( int i = 0 ; i < N ; i++ ) {
        if ( m.count(A[i]) > N/2 ) return i;
    }
    return -1;
}