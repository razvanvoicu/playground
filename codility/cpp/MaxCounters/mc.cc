vector<int> solution(int N, vector<int> &A) {
    vector<int> counters(N);
    int lastMaxVal = -1;
    int currentMax = -1;
    for(unsigned i = 0 ; i < A.size() ; i++ ) {
    	if (A[i] <= N) {
    		counters[A[i]-1] = max(lastMaxVal,counters[A[i]-1])+1;
    		if (counters[A[i]-1] > currentMax) currentMax = counters[A[i]-1];
    	}
    	else
    		lastMaxVal = currentMax;
    }
    for(int i = 0 ; i < N ; i++)
    	counters[i] = max(lastMaxVal,counters[i]);
    return counters;

}