% http://www.spoj.com/problems/PRIME1/

% run with
%    swipl prime1.prolog < prime1.in

:- set_prolog_flag(verbose,silent).
:- prompt(_, '').
:- use_module(library(readutil)).

:- dynamic divisors/2.

main:-
	process,
	halt.

divisors_iter(K,N,Lim,R,[N|R]) :- K > Lim,!.
divisors_iter(K,N,_,L,[K|L]) :- 0 is N mod K,!.
divisors_iter(2,N,Lim,L,R) :- !, divisors_iter(3,N,Lim,L,R).
divisors_iter(K,N,Lim,L,R) :- K1 is K+2, divisors_iter(K1,N,Lim,L,R).

divisors(N,L) :- S is sqrt(N), divisors_iter(2,N,S,[],L).

is_prime(N) :- divisors(N,L), asserta((divisors(N,L) :- !)), L = [N],!,writeln(N).
is_prime(_).

prime_gen2(N1,N2) :- N1 > N2, !.
prime_gen2(N1,N2) :- is_prime(N1), N11 is N1+2, prime_gen2(N11,N2).

prime_gen(2,N2) :- !, writeln(2), prime_gen2(3,N2).
prime_gen(N1,N2) :- 0 is N1 mod 2, !, N11 is N1+1, prime_gen2(N11,N2).
prime_gen(N1,N2) :- prime_gen2(N1,N2).

prime_gen :-
	read_line_to_codes(current_input,In),
	append(S1," ",S),append(S,S2,In),
	number_codes(N1,S1), number_codes(N2,S2),
	prime_gen(N1,N2),
	nl.

runs(0) :- !.
runs(N) :- prime_gen, N1 is N-1, runs(N1).

process:-
	read_line_to_codes(current_input,In),
	number_codes(N,In),
	runs(N).

:- main.
