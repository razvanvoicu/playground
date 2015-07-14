#include <algorithm>
#include <vector>
#include <climits>
#include <iostream>
#include <stack>

using namespace std;

int solution(string &A) {
    int N = A.size();
    stack<int> S;
    for (int i = 0; i < N; i++) {
        if(isdigit(A[i])) S.push(A[i] -'0');
        else {
            if (S.size() < 2) return -1;
            int a = S.top();
            S.pop();
            int b = S.top();
            S.pop();
            int result = 0;
            switch (A[i]) {
                case '+':
                    result = a + b ;
                    break;
                case '*':
                    result = a * b ;
                    break;
            }
            if (result > 0x0FFF) return -1;
            S.push(result);
        }
    }
    if (S.empty()) return -1;
    return S.top();
}

int main() {
    string s = "13+62*7+*" ;
    cout << solution(s) << endl;
    return 0;
}