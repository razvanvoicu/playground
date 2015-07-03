int solution(string &S) {
   int N = S.size();
   int count = 0;
   for ( int i = 0 ; i < N ; i++ ) {
       if ( S[i] == '(' ) count++ ;
       else count -- ;
       if ( count < 0 ) return 0;
   }
   return count == 0 ;
}
