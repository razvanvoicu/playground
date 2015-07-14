#include <set>
#include <algorithm>

int solution(vector<int>& A) {
    int N = A.size();
    set<int> S;
    for ( int i = 0 ; i < N ; i++ ) S.insert(A[i]);
    return S.size();
}