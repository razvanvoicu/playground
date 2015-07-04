int solution(vector<int>& A) {
    int N = A.size();
    int count = 1;
    int left = 0, right = N-1;
    while (left < right) {
        if ( abs((long long)A[left]) > abs((long long)A[right]) ) {
            if (A[left] < A[left+1]) count++ ;
            left++;
        } else if ( abs((long long)A[left]) < abs((long long)A[right]) ) {
            if ( A[right] > A[right-1] ) count++ ;
            right--;
        } else left ++ ;
    }
    return count;
}
