#include <stack>

int solution(vector<int> &A, vector<int> &B) {
    int N = A.size();
    stack<int> s;
    int count = 0 ;
    for ( int i = 0 ; i < N ; i++ ) {
        if ( B[i] == 0 ) {
            while ( !s.empty() && A[i] > s.top() ) s.pop();
            if ( s.empty() ) count ++ ;
        } else {
            s.push(A[i]);
        }
    }
    count += s.size();
    return count;
}