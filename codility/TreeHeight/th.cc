#include <algorithm>

int solution(tree * T) {
	if ( T == NULL ) return -1 ;
	return 1 + max(solution(T->l),solution(T->r));
}