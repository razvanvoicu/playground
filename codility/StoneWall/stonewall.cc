#include <algorithm>
#include <stack>

int solution(vector<int> &H) {
    int N = H.size();
    stack<int> s;
    int count = 0 ;
    for ( int i = 0 ; i < N ; i ++ ) {
        while (s.size() > 0 && s.top() > H[i]) {
            count ++ ;
            s.pop();
        }
        if ( s.size() == 0 || s.top() < H[i] )
            s.push(H[i]);
    }
    while (s.size() > 0) {
        count ++ ;
        s.pop();
    }
    return(count);
}