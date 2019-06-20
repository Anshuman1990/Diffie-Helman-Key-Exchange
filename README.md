# Diffie-Helman-Key-Exchange

Diffie–Hellman key exchange
From Wikipedia, the free encyclopedia
Jump to navigationJump to search

This article has multiple issues. Please help improve it or discuss these issues on the talk page. (Learn how and when to remove these template messages)
This article includes a list of references, but its sources remain unclear because it has insufficient inline citations. (March 2013)
This article possibly contains original research. (November 2014)

Prior to public key methods like Diffie–Hellman, cryptographic keys had to be transmitted in physical form such as this World War II list of keys for the German Enigma cipher machine.
Diffie–Hellman key exchange (DH)[nb 1] is a method of securely exchanging cryptographic keys over a public channel and was one of the first public-key protocols as originally conceptualized by Ralph Merkle and named after Whitfield Diffie and Martin Hellman.[1][2] DH is one of the earliest practical examples of public key exchange implemented within the field of cryptography.

Traditionally, secure encrypted communication between two parties required that they first exchange keys by some secure physical channel, such as paper key lists transported by a trusted courier. The Diffie–Hellman key exchange method allows two parties that have no prior knowledge of each other to jointly establish a shared secret key over an insecure channel. This key can then be used to encrypt subsequent communications using a symmetric key cipher.

Diffie–Hellman is used to secure a variety of Internet services. However, research published in October 2015 suggests that the parameters in use for many DH Internet applications at that time are not strong enough to prevent compromise by very well-funded attackers, such as the security services of large governments.[3]

The scheme was first published by Whitfield Diffie and Martin Hellman in 1976,[2] but in 1997 it was revealed that James H. Ellis,[4] Clifford Cocks, and Malcolm J. Williamson of GCHQ, the British signals intelligence agency, had previously, in 1969,[5] shown how public-key cryptography could be achieved.[6]

Although Diffie–Hellman key agreement itself is a non-authenticated key-agreement protocol, it provides the basis for a variety of authenticated protocols, and is used to provide forward secrecy in Transport Layer Security's ephemeral modes (referred to as EDH or DHE depending on the cipher suite).

The method was followed shortly afterwards by RSA, an implementation of public-key cryptography using asymmetric algorithms.

U.S. Patent 4,200,770, from 1977, is now expired and describes the now-public-domain algorithm. It credits Hellman, Diffie, and Merkle as inventors.


Contents
1	Name
2	Description
2.1	General overview
2.2	Cryptographic explanation
2.3	Secrecy chart
2.4	Generalization to finite cyclic groups
3	Operation with more than two parties
4	Security
4.1	Practical attacks on Internet traffic
5	Other uses
5.1	Encryption
5.2	Forward secrecy
5.3	Password-authenticated key agreement
5.4	Public key
6	See also
7	Notes
8	References
8.1	General references
9	External links
Name
In 2002, Hellman suggested the algorithm be called Diffie–Hellman–Merkle key exchange in recognition of Ralph Merkle's contribution to the invention of public-key cryptography (Hellman, 2002), writing:

The system...has since become known as Diffie–Hellman key exchange. While that system was first described in a paper by Diffie and me, it is a public key distribution system, a concept developed by Merkle, and hence should be called 'Diffie–Hellman–Merkle key exchange' if names are to be associated with it. I hope this small pulpit might help in that endeavor to recognize Merkle's equal contribution to the invention of public key cryptography.[7]
Description
General overview

Illustration of the idea behind Diffie–Hellman key exchange
Diffie–Hellman key exchange establishes a shared secret between two parties that can be used for secret communication for exchanging data over a public network. The conceptual diagram to the right illustrates the general idea of the key exchange by using colors instead of very large numbers.

The process begins by having the two parties, Alice and Bob, agree on an arbitrary starting color that does not need to be kept secret (but should be different every time[3]); in this example, the color is yellow. Each of them also selects a secret color that they keep to themselves – in this case, red and blue-green. The crucial part of the process is that Alice and Bob each mix their own secret color together with their mutually shared color, resulting in orange-tan and light-blue mixtures respectively, and then publicly exchange the two mixed colors. Finally, each of the two mixes the color they received from the partner with their own private color. The result is a final color mixture (yellow-brown in this case) that is identical to the partner's final color mixture.

If a third party listened to the exchange, it would only know the common color (yellow) and the first mixed colors (orange-tan and light-blue), but it would be computationally difficult for this party to determine the final secret color (yellow-brown). In fact, when using large numbers rather than colors, this action is computationally expensive: It is impossible to do in a reasonable amount of time even for modern supercomputers.

Cryptographic explanation
The simplest and the original implementation[2] of the protocol uses the multiplicative group of integers modulo p, where p is prime, and g is a primitive root modulo p. These two values are chosen in this way to ensure that the resulting shared secret can take on any value from 1 to p–1. Here is an example of the protocol, with non-secret values in blue, and secret values in red.

Alice and Bob publicly agree to use a modulus p = 23 and base g = 5 (which is a primitive root modulo 23).
Alice chooses a secret integer a = 4, then sends Bob A = ga mod p
A = 54 mod 23 = 4
Bob chooses a secret integer b = 3, then sends Alice B = gb mod p
B = 53 mod 23 = 10
Alice computes s = Ba mod p
s = 104 mod 23 = 18
Bob computes s = Ab mod p
s = 43 mod 23 = 18
Alice and Bob now share a secret (the number 18).
Both Alice and Bob have arrived at the same value s, because, under mod p,

{\displaystyle {\color {Blue}A}^{\color {Red}b}{\bmod {\color {Blue}p}}={\color {Blue}g}^{\color {Red}ab}{\bmod {\color {Blue}p}}={\color {Blue}g}^{\color {Red}ba}{\bmod {\color {Blue}p}}={\color {Blue}B}^{\color {Red}a}{\bmod {\color {Blue}p}}} {\displaystyle {\color {Blue}A}^{\color {Red}b}{\bmod {\color {Blue}p}}={\color {Blue}g}^{\color {Red}ab}{\bmod {\color {Blue}p}}={\color {Blue}g}^{\color {Red}ba}{\bmod {\color {Blue}p}}={\color {Blue}B}^{\color {Red}a}{\bmod {\color {Blue}p}}}[8]
More specifically,

{\displaystyle ({\color {Blue}g}^{\color {Red}a}{\bmod {\color {Blue}p}})^{\color {Red}b}{\bmod {\color {Blue}p}}=({\color {Blue}g}^{\color {Red}b}{\bmod {\color {Blue}p}})^{\color {Red}a}{\bmod {\color {Blue}p}}} {\displaystyle ({\color {Blue}g}^{\color {Red}a}{\bmod {\color {Blue}p}})^{\color {Red}b}{\bmod {\color {Blue}p}}=({\color {Blue}g}^{\color {Red}b}{\bmod {\color {Blue}p}})^{\color {Red}a}{\bmod {\color {Blue}p}}}
Note that only a, b, and (gab mod p = gba mod p) are kept secret. All the other values – p, g, ga mod p, and gb mod p – are sent in the clear. Once Alice and Bob compute the shared secret they can use it as an encryption key, known only to them, for sending messages across the same open communications channel.

Of course, much larger values of a, b, and p would be needed to make this example secure, since there are only 23 possible results of n mod 23. However, if p is a prime of at least 600 digits, then even the fastest modern computers cannot find a given only g, p and ga mod p. Such a problem is called the discrete logarithm problem.[3] The computation of ga mod p is known as modular exponentiation and can be done efficiently even for large numbers. Note that g need not be large at all, and in practice is usually a small integer (like 2, 3, ...).

Secrecy chart
The chart below depicts who knows what, again with non-secret values in blue, and secret values in red. Here Eve is an eavesdropper – she watches what is sent between Alice and Bob, but she does not alter the contents of their communications.

g = public (prime) base, known to Alice, Bob, and Eve. g = 5
p = public (prime) modulus, known to Alice, Bob, and Eve. p = 23
a = Alice's private key, known only to Alice. a = 6
b = Bob's private key known only to Bob. b = 15
A = Alice's public key, known to Alice, Bob, and Eve. A = ga mod p = 8
B = Bob's public key, known to Alice, Bob, and Eve. B = gb mod p = 19
Alice
Known	Unknown
p = 23	
g = 5	
a = 6	b
A = 5a mod 23	
A = 56 mod 23 = 8	
B = 19	
s = Ba mod 23	
s = 196 mod 23 = 2	
Bob
Known	Unknown
p = 23	
g = 5	
b = 15	a
B = 5b mod 23	
B = 515 mod 23 = 19	
A = 8	
s = Ab mod 23	
s = 815 mod 23 = 2	
Eve
Known	Unknown
p = 23	
g = 5	
a, b
 	 
 	 
A = 8, B = 19	
 	 
s
Now s is the shared secret key and it is known to both Alice and Bob, but not to Eve.

Note: It should be difficult for Alice to solve for Bob's private key or for Bob to solve for Alice's private key. If it is not difficult for Alice to solve for Bob's private key (or vice versa), Eve may simply substitute her own private / public key pair, plug Bob's public key into her private key, produce a fake shared secret key, and solve for Bob's private key (and use that to solve for the shared secret key. Eve may attempt to choose a public / private key pair that will make it easy for her to solve for Bob's private key).

Another demonstration of Diffie–Hellman (also using numbers too small for practical use) is given here.[9]

Generalization to finite cyclic groups
Here is a more general description of the protocol:[10]

Alice and Bob agree on a finite cyclic group G of order n and a generating element g in G. (This is usually done long before the rest of the protocol; g is assumed to be known by all attackers.) The group G is written multiplicatively.
Alice picks a random natural number a, where 1 ≤ a < n, and sends ga to Bob.
Bob picks a random natural number b, which is also 1 ≤ b < n, and sends gb to Alice.
Alice computes (gb)a.
Bob computes (ga)b.
Both Alice and Bob are now in possession of the group element gab, which can serve as the shared secret key. The group G satisfies the requisite condition for secure communication if there is not an efficient algorithm for determining gab given g, ga, and gb.

For example, the elliptic curve Diffie–Hellman protocol is a variant that uses elliptic curves instead of the multiplicative group of integers modulo p. Variants using hyperelliptic curves have also been proposed. The supersingular isogeny key exchange is a Diffie–Hellman variant that has been designed to be secure against quantum computers.

Operation with more than two parties
Diffie–Hellman key agreement is not limited to negotiating a key shared by only two participants. Any number of users can take part in an agreement by performing iterations of the agreement protocol and exchanging intermediate data (which does not itself need to be kept secret). For example, Alice, Bob, and Carol could participate in a Diffie–Hellman agreement as follows, with all operations taken to be modulo p:

The parties agree on the algorithm parameters p and g.
The parties generate their private keys, named a, b, and c.
Alice computes ga and sends it to Bob.
Bob computes (ga)b = gab and sends it to Carol.
Carol computes (gab)c = gabc and uses it as her secret.
Bob computes gb and sends it to Carol.
Carol computes (gb)c = gbc and sends it to Alice.
Alice computes (gbc)a = gbca = gabc and uses it as her secret.
Carol computes gc and sends it to Alice.
Alice computes (gc)a = gca and sends it to Bob.
Bob computes (gca)b = gcab = gabc and uses it as his secret.
An eavesdropper has been able to see ga, gb, gc, gab, gac, and gbc, but cannot use any combination of these to efficiently reproduce gabc.

To extend this mechanism to larger groups, two basic principles must be followed:

Starting with an "empty" key consisting only of g, the secret is made by raising the current value to every participant’s private exponent once, in any order (the first such exponentiation yields the participant’s own public key).
Any intermediate value (having up to N-1 exponents applied, where N is the number of participants in the group) may be revealed publicly, but the final value (having had all N exponents applied) constitutes the shared secret and hence must never be revealed publicly. Thus, each user must obtain their copy of the secret by applying their own private key last (otherwise there would be no way for the last contributor to communicate the final key to its recipient, as that last contributor would have turned the key into the very secret the group wished to protect).
These principles leave open various options for choosing in which order participants contribute to keys. The simplest and most obvious solution is to arrange the N participants in a circle and have N keys rotate around the circle, until eventually every key has been contributed to by all N participants (ending with its owner) and each participant has contributed to N keys (ending with their own). However, this requires that every participant perform N modular exponentiations.

By choosing a more optimal order, and relying on the fact that keys can be duplicated, it is possible to reduce the number of modular exponentiations performed by each participant to log2(N) + 1 using a divide-and-conquer-style approach, given here for eight participants:

Participants A, B, C, and D each perform one exponentiation, yielding gabcd; this value is sent to E, F, G, and H. In return, participants A, B, C, and D receive gefgh.
Participants A and B each perform one exponentiation, yielding gefghab, which they send to C and D, while C and D do the same, yielding gefghcd, which they send to A and B.
Participant A performs an exponentiation, yielding gefghcda, which it sends to B; similarly, B sends gefghcdb to A. C and D do similarly.
Participant A performs one final exponentiation, yielding the secret gefghcdba = gabcdefgh, while B does the same to get gefghcdab = gabcdefgh; again, C and D do similarly.
Participants E through H simultaneously perform the same operations using gabcd as their starting point.
Once this operation has been completed all participants will possess the secret gabcdefgh, but each participant will have performed only four modular exponentiations, rather than the eight implied by a simple circular arrangement.

Security
The protocol is considered secure against eavesdroppers if G and g are chosen properly. In particular, the order of the group G must be large, particularly if the same group is used for large amounts of traffic. The eavesdropper ("Eve") has to solve the Diffie–Hellman problem to obtain gab. This is currently considered difficult for groups whose order is large enough. An efficient algorithm to solve the discrete logarithm problem would make it easy to compute a or b and solve the Diffie–Hellman problem, making this and many other public key cryptosystems insecure. Fields of small characteristic may be less secure.[11]

The order of G should have a large prime factor to prevent use of the Pohlig–Hellman algorithm to obtain a or b. For this reason, a Sophie Germain prime q is sometimes used to calculate p = 2q + 1, called a safe prime, since the order of G is then only divisible by 2 and q. g is then sometimes chosen to generate the order q subgroup of G, rather than G, so that the Legendre symbol of ga never reveals the low order bit of a. A protocol using such a choice is for example IKEv2.[12]

g is often a small integer such as 2. Because of the random self-reducibility of the discrete logarithm problem a small g is equally secure as any other generator of the same group.

If Alice and Bob use random number generators whose outputs are not completely random and can be predicted to some extent, then Eve's task is much easier.

In the original description, the Diffie–Hellman exchange by itself does not provide authentication of the communicating parties and is thus vulnerable to a man-in-the-middle attack. Mallory (an active attacker executing the man-in-the-middle attack) may establish two distinct key exchanges, one with Alice and the other with Bob, effectively masquerading as Alice to Bob, and vice versa, allowing her to decrypt, then re-encrypt, the messages passed between them. Note that Mallory must continue to be in the middle, actively decrypting and re-encrypting messages every time Alice and Bob communicate. If she is ever absent, her previous presence is then revealed to Alice and Bob. They will know that all of their private conversations had been intercepted and decoded by someone in the channel. In most cases it will not help them get Mallory's private key, even if she used the same key for both exchanges.

A method to authenticate the communicating parties to each other is generally needed to prevent this type of attack. Variants of Diffie–Hellman, such as STS protocol, may be used instead to avoid these types of attacks.

Practical attacks on Internet traffic
The number field sieve algorithm, which is generally the most effective in solving the discrete logarithm problem, consists of four computational steps. The first three steps only depend on the order of the group G, not on the specific number whose finite log is desired.[13] It turns out that much Internet traffic uses one of a handful of groups that are of order 1024 bits or less.[3] By precomputing the first three steps of the number field sieve for the most common groups, an attacker need only carry out the last step, which is much less computationally expensive than the first three steps, to obtain a specific logarithm. The Logjam attack used this vulnerability to compromise a variety of Internet services that allowed the use of groups whose order was a 512-bit prime number, so called export grade. The authors needed several thousand CPU cores for a week to precompute data for a single 512-bit prime. Once that was done, individual logarithms could be solved in about a minute using two 18-core Intel Xeon CPUs.[3]

As estimated by the authors behind the Logjam attack, the much more difficult precomputation needed to solve the discrete log problem for a 1024-bit prime would cost on the order of $100 million, well within the budget of large national intelligence agency such as the U.S. National Security Agency (NSA). The Logjam authors speculate that precomputation against widely reused 1024-bit DH primes is behind claims in leaked NSA documents that NSA is able to break much of current cryptography.[3]

To avoid these vulnerabilities, authors recommend use of elliptic curve cryptography, for which no similar attack is known. Failing that, they recommend that the order, p, of the Diffie–Hellman group should be at least 2048 bits. They estimate that the pre-computation required for a 2048-bit prime is 109 more difficult than for 1024-bit primes.[3]

Other uses
Encryption
Public key encryption schemes based on the Diffie–Hellman key exchange have been proposed. The first such scheme is the ElGamal encryption. A more modern variant is the Integrated Encryption Scheme.

Forward secrecy
Protocols that achieve forward secrecy generate new key pairs for each session and discard them at the end of the session. The Diffie–Hellman key exchange is a frequent choice for such protocols, because of its fast key generation.

Password-authenticated key agreement
When Alice and Bob share a password, they may use a password-authenticated key agreement (PK) form of Diffie–Hellman to prevent man-in-the-middle attacks. One simple scheme is to compare the hash of s concatenated with the password calculated independently on both ends of channel. A feature of these schemes is that an attacker can only test one specific password on each iteration with the other party, and so the system provides good security with relatively weak passwords. This approach is described in ITU-T Recommendation X.1035, which is used by the G.hn home networking standard.

An example of such a protocol is the Secure Remote Password protocol.

Public key
It is also possible to use Diffie–Hellman as part of a public key infrastructure, allowing Bob to encrypt a message so that only Alice will be able to decrypt it, with no prior communication between them other than Bob having trusted knowledge of Alice's public key. Alice's public key is {\displaystyle (g^{a}{\bmod {p}},g,p)} (g^{a}{\bmod {p}},g,p). To send her a message, Bob chooses a random b and then sends Alice {\displaystyle g^{b}{\bmod {p}}} g^{b}{\bmod {p}} (unencrypted) together with the message encrypted with symmetric key {\displaystyle (g^{a})^{b}{\bmod {p}}} (g^{a})^{b}{\bmod {p}}. Only Alice can determine the symmetric key and hence decrypt the message because only she has a (the private key). A pre-shared public key also prevents man-in-the-middle attacks.

In practice, Diffie–Hellman is not used in this way, with RSA being the dominant public key algorithm. This is largely for historical and commercial reasons[citation needed], namely that RSA Security created a certificate authority for key signing that became Verisign. Diffie–Hellman cannot be used to sign certificates. However, the ElGamal and DSA signature algorithms are mathematically related to it, as well as MQV, STS and the IKE component of the IPsec protocol suite for securing Internet Protocol communications.

See also
Elliptic-curve Diffie–Hellman key exchange
Notes
 Synonyms of Diffie–Hellman key exchange include:
Diffie–Hellman–Merkle key exchange
Diffie–Hellman key agreement
Diffie–Hellman key establishment
Diffie–Hellman key negotiation
Exponential key exchange
Diffie–Hellman protocol
Diffie–Hellman handshake
References
 Merkle, Ralph C. (April 1978). "Secure Communications Over Insecure Channels". Communications of the ACM. 21 (4): 294–299. CiteSeerX 10.1.1.364.5157. doi:10.1145/359460.359473. Received August, 1975; revised September 1977
 Diffie, Whitfield; Hellman, Martin E. (November 1976). "New Directions in Cryptography" (PDF). IEEE Transactions on Information Theory. 22 (6): 644–654. CiteSeerX 10.1.1.37.9720. doi:10.1109/TIT.1976.1055638. Archived (PDF) from the original on 2014-11-29.
 Adrian, David; et al. (October 2015). "Imperfect Forward Secrecy: How Diffie–Hellman Fails in Practice" (PDF).
 Ellis, J. H. (January 1970). "The possibility of Non-Secret digital encryption" (PDF). CESG Research Report. Archived from the original (PDF) on 2014-10-30. Retrieved 2015-08-28.
 "The Possibility of Secure Non-Secret Digital Encryption" (PDF). Archived (PDF) from the original on 2017-02-16. Retrieved 2017-07-08.
 "GCHQ trio recognised for key to secure shopping online". BBC News. 5 October 2010. Archived from the original on 10 August 2014. Retrieved 5 August 2014.
 Hellman, Martin E. (May 2002), "An overview of public key cryptography" (PDF), IEEE Communications Magazine, 40 (5): 42–49, CiteSeerX 10.1.1.127.2652, doi:10.1109/MCOM.2002.1006971, archived (PDF) from the original on 2016-04-02
 Garzia, F. (2013), Handbook of Communications Security, WIT Press, p. 182, ISBN 978-1845647681
 Buchanan, Bill, "Diffie–Hellman Example in ASP.NET", Bill's Security Tips, archived from the original on 2011-08-27, retrieved 2015-08-27
 Buchmann, Johannes A. (2013). Introduction to Cryptography (Second ed.). Springer Science+Business Media. pp. 190–191. ISBN 978-1-4419-9003-7.
 Barbulescu, Razvan; Gaudry, Pierrick; Joux, Antoine; Thomé, Emmanuel (2014). "A Heuristic Quasi-Polynomial Algorithm for Discrete Logarithm in Finite Fields of Small Characteristic" (PDF). Advances in Cryptology – EUROCRYPT 2014. Proceedings 33rd Annual International Conference on the Theory and Applications of Cryptographic Techniques. Lecture Notes in Computer Science. 8441. Copenhagen, Denmark. pp. 1–16. doi:10.1007/978-3-642-55220-5_1. ISBN 978-3-642-55220-5.
 C. Kaufman (Microsoft) (December 2005). "RFC 4306 Internet Key Exchange (IKEv2) Protocol". Internet Engineering Task Force (IETF). Archived from the original on 2015-01-07.
 Whitfield Diffie, Paul C. Van Oorschot, and Michael J. Wiener "Authentication and Authenticated Key Exchanges", in Designs, Codes and Cryptography, 2, 107–125 (1992), Section 5.2, available as Appendix B to U.S. Patent 5,724,425
General references
Gollman, Dieter (2011). Computer Security (2nd ed.). West Sussex, England: John Wiley & Sons, Ltd. ISBN 978-0470741153.
Williamson, Malcolm J. (January 21, 1974). Non-secret encryption using a finite field (PDF) (Technical report). Communications Electronics Security Group. Retrieved 2017-03-22.
Williamson, Malcolm J. (August 10, 1976). Thoughts on Cheaper Non-Secret Encryption (PDF) (Technical report). Communications Electronics Security Group. Retrieved 2015-08-25.
The History of Non-Secret Encryption JH Ellis 1987 (28K PDF file) (HTML version)
The First Ten Years of Public-Key Cryptography Whitfield Diffie, Proceedings of the IEEE, vol. 76, no. 5, May 1988, pp: 560–577 (1.9MB PDF file)
Menezes, Alfred; van Oorschot, Paul; Vanstone, Scott (1997). Handbook of Applied Cryptography Boca Raton, Florida: CRC Press. ISBN 0-8493-8523-7. (Available online)
Singh, Simon (1999) The Code Book: the evolution of secrecy from Mary Queen of Scots to quantum cryptography New York: Doubleday ISBN 0-385-49531-5
An Overview of Public Key Cryptography Martin E. Hellman, IEEE Communications Magazine, May 2002, pp. 42–49. (123kB PDF file)
External links

This article's use of external links may not follow Wikipedia's policies or guidelines. Please improve this article by removing excessive or inappropriate external links, and converting useful links where appropriate into footnote references. (March 2016) (Learn how and when to remove this template message)
Oral history interview with Martin Hellman, Charles Babbage Institute, University of Minnesota. Leading cryptography scholar Martin Hellman discusses the circumstances and fundamental insights of his invention of public key cryptography with collaborators Whitfield Diffie and Ralph Merkle at Stanford University in the mid-1970s.
RFC 2631 – Diffie–Hellman Key Agreement Method. E. Rescorla. June 1999.
RFC 3526 – More Modular Exponential (MODP) Diffie–Hellman groups for Internet Key Exchange (IKE). T. Kivinen, M. Kojo, SSH Communications Security. May 2003.
Summary of ANSI X9.42: Agreement of Symmetric Keys Using Discrete Logarithm Cryptography (64K PDF file) (Description of ANSI 9 Standards)
Talk by Martin Hellman in 2007, YouTube video
Crypto dream team Diffie & Hellman wins $1M 2015 Turing Award (a.k.a. "Nobel Prize of Computing")
icon	Cryptography portal
vte
Public-key cryptography
Algorithms	
Integer factorization	
Benaloh Blum–Goldwasser Cayley–Purser Damgård–Jurik GMR Goldwasser–Micali Naccache–Stern Paillier Rabin RSA Okamoto–Uchiyama Schmidt–Samoa
Discrete logarithm	
BLS Cramer–Shoup DH DSA ECDH ECDSA EdDSA EKE ElGamal signature scheme MQV Schnorr SPEKE SRP STS
Lattice/SVP/CVP/LWE/SIS	
NTRUEncrypt NTRUSign RLWE-KEX RLWE-SIG BLISS
Others	
AE CEILIDH EPOC HFE IES Lamport McEliece Merkle–Hellman Naccache–Stern knapsack cryptosystem Three-pass protocol XTR
Theory	
Discrete logarithm Elliptic-curve cryptography Non-commutative cryptography RSA problem Trapdoor function
Standardization	
CRYPTREC IEEE P1363 NESSIE NSA Suite B Post-Quantum Cryptography Standardization
Topics	
Digital signature OAEP Fingerprint PKI Web of trust Key size Post-quantum cryptography
vte
Cryptography
Categories: Key-agreement protocolsPublic-key cryptography
Navigation menu
Not logged inTalkContributionsCreate accountLog inArticleTalkReadEditView historySearch
Search Wikipedia
Main page
Contents
Featured content
Current events
Random article
Donate to Wikipedia
Wikipedia store
Interaction
Help
About Wikipedia
Community portal
Recent changes
Contact page
Tools
What links here
Related changes
Upload file
Special pages
Permanent link
Page information
Wikidata item
Cite this page
In other projects
Wikimedia Commons
Print/export
Create a book
Download as PDF
Printable version

Languages
العربية
Deutsch
Español
Français
한국어
日本語
Português
Русский
中文
19 more
Edit links
This page was last edited on 17 June 2019, at 10:10 (UTC).
Text is available under the Creative Commons Attribution-ShareAlike License; additional terms may apply. By using this site, you agree to the Terms of Use and Privacy Policy. Wikipedia® is a registered trademark of the Wikimedia Foundation, Inc., a non-profit organization.
Privacy policyAbout WikipediaDisclaimersContact WikipediaDevelopersCookie statementMobile viewWikimedia Foundation Powered by MediaWiki
