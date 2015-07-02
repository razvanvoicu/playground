#include <algorithm>
#include <random>
#include <iostream>
#include <climits>

using namespace std;

int solution(vector<int> &A) {
	int N = A.size();

	long long sum = 0;
	for ( int i = 1 ; i < N ; i ++ ) sum += A[i];

	long long left = 0, right = sum ;
	for ( int P = 0 ; P < N ; P ++ ) {
		if ( left == right ) return P;
		else if ( P < N - 1 ) {
			left += A[P];
			right -= A[P+1] ;
		}
	}

	return -1 ;

}

void testEmpty() {
	vector<int> A;
	cout << "Starting testEmpty" << endl;
	int r = solution(A);
	cout << "Returned from testEmpty" << endl;
	if ( r != -1 ) cout << "testEmpty failed" ;
	else cout << "testEmpty succeeded" ;
	cout << endl;
}

void testSmallSucceeding(int size) {
	vector<int> A(2*size+1);
	for ( int i = 0 ; i < size ; i ++ ) {
		A[i] = rand();
		A[2*size-i] = A[i];
	}
	cout << "Starting testSmallSucceeding with size " << 2*size+1 << endl;
	int r = solution(A);
	cout << "Returned from testSmallSucceeding" << endl;
	if ( r != size ) {
		cout << "testSmallSucceeding failed: expected " << size << " but got " << r << endl;
		exit(1);
	} else cout << "testSmallSucceeding succeeded as expected" << endl ;
}

void testSmallNonSucceeding(int size) {
	if (size < 2) {
		cout << "testSmallNonSucceeding should only be used with size >= 2" << endl;
	}
	vector<int> A(size);
	for ( int i = 0 ; i < size ; i ++ ) {
		A[i] = rand()+1;
	}
	cout << "Starting testSmallNonSucceeding with size " << size << endl;
	int r = solution(A);
	cout << "Returned from testSmallNonSucceeding" << endl;
	if ( r != -1 ) {
		cout << "testSmallNonSucceeding failed: expected " << -1 << " but got " << r << endl;
		exit(1);
	} else cout << "testSmallNonSucceeding succeeded as expected" << endl ;
}

void testLargeNumbersSucceeding(int size) {
	vector<int> A(2*size+1);
	for ( int i = 0 ; i < size ; i ++ ) {
		A[i] = INT_MAX;
		A[2*size-i] = A[i];
	}
	cout << "Starting testLargeNumbersSucceeding with size " << 2*size+1 << endl;
	int r = solution(A);
	cout << "Returned from testLargeNumbersSucceeding" << endl;
	if ( r != size ) {
		cout << "testLargeNumbersSucceeding failed: expected " << size << " but got " << r << endl;
		exit(1);
	} else cout << "testLargeNumbersSucceeding succeeded as expected" << endl ;
}

void testLargeNumbersNonSucceeding(int size) {
	if (size < 2) {
		cout << "testLargeNumbersNonSucceeding should only be used with size >= 2" << endl;
	}
	vector<int> A(size);
	for ( int i = 0 ; i < size ; i ++ ) {
		A[i] = INT_MAX;
	}
	cout << "Starting testLargeNumbersNonSucceeding with size " << size << endl;
	int r = solution(A);
	cout << "Returned from testLargeNumbersNonSucceeding" << endl;
	if ( r != -1 ) {
		cout << "testLargeNumbersNonSucceeding failed: expected " << -1 << " but got " << r << endl;
		exit(1);
	} else cout << "testLargeNumbersNonSucceeding succeeded as expected" << endl ;
}

void test() {
	vector<int> A({-1,3,-4,5,1,-6,2,1});
	if(solution(A)==1) cout << "Example test passed " << endl;
	else cout << "Example test failed." << endl;
}

int main() {
	testEmpty();
	testSmallSucceeding(10);
	testSmallSucceeding(100);
	testSmallSucceeding(1000);
	testSmallSucceeding(10000);
	testSmallSucceeding(0);
	testSmallNonSucceeding(2);
	testSmallNonSucceeding(10);
	testSmallNonSucceeding(100);
	testSmallNonSucceeding(1000);
	testSmallNonSucceeding(10000);
	testLargeNumbersSucceeding(2);
	testLargeNumbersSucceeding(10);
	testLargeNumbersSucceeding(100);
	testLargeNumbersSucceeding(1000);
	testLargeNumbersSucceeding(10000);
	testLargeNumbersNonSucceeding(2);
	testLargeNumbersNonSucceeding(10);
	testLargeNumbersNonSucceeding(100);
	testLargeNumbersNonSucceeding(1000);
	testLargeNumbersNonSucceeding(10000);
	test();
}

