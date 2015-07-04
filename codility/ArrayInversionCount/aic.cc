
long long merge(vector<int>& src, vector<int>& dest, int left, int mid, int right) {
	int i = left ;
	int j = mid ;
	int k = left ;
	long long inv = 0 ;
	while ( i < mid && j < right ) {
		if ( src[i] <= src[j] ) {
			dest[k++] = src[i++] ;
		} else {
			dest[k++] = src[j++] ;
			inv += mid-i ;
			if ( inv > 1000000000 ) return -1 ;
		}
	}
	while ( i < mid ) dest[k++] = src[i++] ;
	while ( j < right ) dest[k++] = src[j++] ;
	return inv;
}

long long mergeSort(vector<int>& src, vector<int>& aux, int left, int mid, int right) {
	if ( right - left <= 1 ) return 0;
	long long x = mergeSort(src,aux,left,left+(mid-left)/2,mid);
	long long y = mergeSort(src,aux,mid,mid+(right-mid)/2,right);
	long long z = merge(src,aux,left,mid,right) ;
	for ( int i = left ; i < right ; i++ ) src[i] = aux[i] ;
	long long r = x + y + z ;
	if ( x == -1 || y == -1 || z == -1 || r > 1000000000 ) return -1 ;
	return r;
}

int solution(vector<int> &A) {
	int N = A.size();
	vector<int> X(N);
	return mergeSort(A,X,0,N/2,N);
}
