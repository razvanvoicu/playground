#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

int idx(char nt) {
	switch(nt) {
		case 'A' : return 0;
		case 'C' : return 1;
		case 'G' : return 2;
		case 'T' : return 3;
	}
	return -1;
}

void updateNt(string &s, vector<vector<int>*>& nt, int i, vector<int>& prev) {
	for ( int j = 0 ; j < 4 ; j ++ ) {
		if ( idx(s[i]) == j) (*nt[i])[j] = prev[j] + 1;
		else (*nt[i])[j] = prev[j] ;
	}
}

vector<int> solution(string &S, vector<int> &P, vector<int> &Q) {
	int N = S.size();
	int M = P.size();
	vector<vector<int>*> nt(N);
	for ( int i = 0 ; i < N ; i++ ) nt[i] = new vector<int>(4);
	vector<int> result(M);
	updateNt(S,nt,0,*(new vector<int>(4)));
	for(int i = 1 ; i < N ; i++) 
		updateNt(S,nt,i,*nt[i-1]);
	for(int i = 0 ; i < M ; i ++ ) {
		for(int j = 0 ; j < 4 ; j ++) {
			if ((P[i]==0 && (*nt[Q[i]])[j] > 0) || (P[i] !=0 && (*nt[Q[i]])[j] - (*nt[P[i]-1])[j] != 0)) {
				result[i] = j + 1;
				break;
			} 
		}
	}
	return result;
}

void prt(vector<int>& x) {
	for(unsigned i = 0 ; i < x.size() ; i++) cout << " " << x[i] ;
	cout << endl ;
}

int main() {
	string input = "C";
	vector<int> P({0});
	vector<int> Q({0});
	vector<int> r = solution(input,P,Q);
	prt(r);
}