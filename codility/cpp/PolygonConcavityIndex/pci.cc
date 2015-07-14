#include <vector>
#include <iostream>
#include <algorithm>
#include <string>
#include <climits>

long long threePt(vector<Point2D>& A, int i) {
    int N = A.size();
    long long distY2 = A[(i + 2) % N].y; distY2 -= A[i].y;
    long long distX1 = A[(i + 1) % N].x; distX1 -= A[i].x;
    long long distX2 = A[(i + 2) % N].x; distX2 -= A[i].x;
    long long distY1 = A[(i + 1) % N].y; distY1 -= A[i].y;
    return distY2*distX1 - distX2*distY1;
}

int clockwise(int N, int E, int S, int W) {
    if (N == E && S == W || N == W && S == E) return -1;
    bool a = N <= E && E <= S && S <= W;
    bool b = E <= S && S <= W && W <= N;
    bool c = S <= W && W <= N && N <= E;
    bool d = W <= N && N <= E && E <= S;
    return a || b || c || d;
}

int solution(vector<Point2D>& A) {
    int N = A.size();
    int countPos = 0, countNeg = 0;
    int lastPos = -1, lastNeg = -1;
    long long sum = 0L;
    int north = INT_MIN, south = INT_MAX, west = INT_MAX, east = INT_MIN;
    int northI=-1, southI=-1, westI=-1, eastI=-1;
    int rN = INT_MIN, rS = INT_MAX, rW = INT_MAX, rE = INT_MIN;
    int rNi = -1, rSi = -1, rWi = -1, rEi = -1;
    for (int i = 0 ; i < N ; i++ ) {
        long long x = threePt(A, i);
        sum += x;
        if (x > 0) {
            countPos++;
            lastPos = i;
        }
        else if (x < 0) {
            countNeg++;
            lastNeg = i;
        }
        if (A[i].x > east) {
            east = A[i].x; eastI = i;
        }
        if (A[i].x < west) {
            west = A[i].x; westI = i;
        }
        if (A[i].y > north) {
            north = A[i].y; northI = i;
        }
        if (A[i].y < south) {
            south = A[i].y; southI = i;
        }
        if (A[i].x - A[i].y > rE) {
            rE = A[i].x - A[i].y; rEi = i;
        }
        if (A[i].x - A[i].y < rW) {
            rW = A[i].x - A[i].y; rWi = i;
        }
        if (A[i].x + A[i].y > rN) {
            rN = A[i].x + A[i].y; rNi = i;
        }
        if (A[i].x + A[i].y < rS) {
            rS = A[i].x + A[i].y; rSi = i;
        }
    }
    int c = clockwise(northI, eastI, southI, westI);
    if (c == 1) {
        if (countNeg == N) return -1;
        else return (lastPos + 1) % N;
    }
    else if (c == 0) {
        if (countPos == N) return -1;
        else return (lastNeg + 1) % N;
    }
    else {
        if (clockwise(rNi, rEi, rSi, rWi)) {
            if (countNeg == N) return -1;
            else return (lastPos + 1) % N;
        }
        else {
            if (countPos == N) return -1;
            else return (lastNeg + 1) % N;
        }
    }
    return -1;
}
