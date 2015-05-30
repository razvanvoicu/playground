% http://www.spoj.com/problems/TEST/

% run with
%     swipl test.prolog < test.in

?- use_module(library(readutil)).

?- prompt1('').

test(42) :- !.
test(X) :- writeln(X),fail.

?- current_input(In),repeat, read_line_to_codes(In,X), number_codes(N,X),test(N),!.

?- halt.
