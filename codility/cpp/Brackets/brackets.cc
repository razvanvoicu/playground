#include <stack>

int solution(string &S) {
    stack<char> s;
    int N = S.size();
    for ( int i = 0 ; i < N ; i++ ) {
        switch ( S[i] ) {
            case '(' :
            case '[' :
            case '{' :
                s.push(S[i]);
                break;
            case ')' :
                if ( s.empty() || s.top() != '(' ) return 0 ;
                s.pop();
                break;
            case ']' :
                if ( s.empty() || s.top() != '[' ) return 0 ;
                s.pop();
                break;
            case '}' :
                if ( s.empty() || s.top() != '{' ) return 0 ;
                s.pop();
                break;
        }
    }
    return s.empty();
}