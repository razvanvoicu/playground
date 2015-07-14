int solution(int M, vector<int> &A) {
    int N = A.size();
    vector<int> O(M+1);
    for ( int j = 0 ; j <= M ; j ++ ) O[j] = -1;
    int sliceBeg = 0 ;
    long long count = 0 ;
    for ( int i = 0 ; i < N ; i++ ) {
        if (O[A[i]] != -1) sliceBeg = max(sliceBeg,O[A[i]]+1);
        int d = i - sliceBeg + 1;
        count += d ;
        if (count > 1000000000L) return 1000000000L;
        O[A[i]] = i;
    }
    return count;
}
