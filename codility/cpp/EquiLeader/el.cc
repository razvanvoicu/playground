#include <set>

int solution(vector<int> &A) {
    multiset<int> m;
    unsigned N = A.size();
    for ( unsigned i = 0 ; i < N ; i++ ) {
        m.insert(A[i]);
    }
    int leader = -1 ;
    for ( unsigned i = 0 ; i < N ; i++ ) {
        if ( m.count(A[i]) > N/2 ) {
            leader = A[i];
            break;
        }
    }
    if ( leader == -1 ) return 0;
    unsigned count = 0 ;
    unsigned countL = 0, countR = m.count(leader);
    for ( unsigned i = 0 ; i < N-1 ; i++ ) {
        if ( A[i] == leader ) {
            countL ++;
            countR --;
        }
        if ( countL > (i+1)/2 && countR > (N-i-1)/2 ) count++ ;
    }
    return count;
}