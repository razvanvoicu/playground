#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;
/*
General idea:
    * compute prefix sums into a new vector
    * sort created vector
    * slices of sum 0 will be found by identifying constant
      value segments in the sorted vector.
    * each segment contributes length*(length-1)/2
*/
int solution(vector<int> &A) {
    int N = A.size();
    vector<long long> PS(N+1);
    long long sum = 0;
    PS[0] = 0;
    for (int i = 0; i < N; i++) {
        sum += A[i] ;
        PS[i+1] = sum;
    }

    sort(PS.begin(),PS.end());

    long long count = 0, countSame = 0;
    for (int i = 0; i < N; i++) {
        if (PS[i]==PS[i+1]) countSame++;
        else {
            count += countSame * (countSame+1) / 2 ;
            countSame = 0;
        }
    }
    count += countSame * (countSame+1) / 2 ;
    if (count > 1000000000) return -1;
    return count;
}

int main() {
    {
        vector<int> A;
        cout << "empty " << solution(A) << endl;
    }
    {
        vector<int> B({0,0});
        cout << "0,0 " << solution(B) << endl;
    }
    {
        vector<int> B({0});
        cout << "0 " << solution(B) << endl;
    }
    {
        vector<int> B({1});
        cout << "1 " << solution(B) << endl;
    }
    {
        vector<int> B({5,0});
        cout << "5,0 " << solution(B) << endl;
    }
    {
        vector<int> B({0,5});
        cout << "5,0 " << solution(B) << endl;
    }
    {
        vector<int> B({2, -2, 3, 0, 4, -7});
        cout << "2, -2, 3, 0, 4, -7 " << solution(B) << endl;
    }
    {
        vector<int> B({2, -2, 3, 10, 0, -10, 4, -7});
        cout << "2, -2, 3, 10, 0, -10, 4, -7 " << solution(B) << endl;
    }
    {
        vector<int> B({1, 1, 1, -1, -1, -1});
        cout << "1, 1, 1, -1, -1, -1 " << solution(B) << endl;
    }

    return 0;
}