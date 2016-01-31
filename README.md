# Nickel - Rock-Paper-Scissors-Lizard-Spock Bot

Nickel is a [rock-paper-scissors-lizard-spock](http://www.samkass.com/theories/RPSSL.html) bot which tries to make the best move by predicting the opponent's next move using a set of strategies. There is no way a computer can consistently beat its opponent if the opponent plays randomly. However, humans tend to be unrandom. A computer which employs some predicting algorithms to exploit patterns in the opponent can increase its win rate.

Nickel is inspired by [Iocaine Powder](http://www.ofb.net/~egnor/iocaine.html), which won the [First International RoShamBo Programming Competition](https://webdocs.cs.ualberta.ca/~darse/rsb-results1.html) in 1999. Iocaine Powder has three primary strategies and expands each primary strategy into six strategies using an addition layer of meta-strategies. Nickel follows the same architecture. The meta-strategies of Nickel are same as those in Iocaine Power except that some modifications are needed to cater the rules of rock-paper-scissors-lizard-spock. The major difference between the two bots is that Nickel has a wider repertoire of primary strategies. The additional strategies are commonly used by humans when playing rock-paper-scissors-lizard-spock.

## Table of Contents

* [Build and Run](#build-and-run)
* [Strategies](#strategies)
* [Meta-strategies](#meta-strategies)
* [Strategy Selection](#strategy-selection)
* [Benchmark](#benchmark)
* [Copyright and License](#copyright-and-license)
* [See Also](#see-also)

## Build and Run

To build the project:

```
mvn package
```

To launch the graphical user interface:

```
java -cp target/Nickel-1.0-SNAPSHOT.jar com.byronlai.nickel.App
```

To launch the command line interface:

```
java -cp target/Nickel-1.0-SNAPSHOT.jar com.byronlai.nickel.App --cli
```

## Strategies

A strategy is a method to predict the opponent's next move. Here are the primary strategies that Nickel employs.

### Fixed Move

This strategy always returns the same prediction. This may sound stupid but humans often repeat his last move. Therefore it is beneficial to include this strategy.

### Random Strategy

This strategy predicts the opponent's next move by randomly choosing a move. When combining with a meta-strategy, it can prevent us from having a devastating loss when we start losing because there is no way to guess our next move if we choose at random.

### Frequency Counting

This strategy examines the opponent's history to find their most frequent move and assumes that they will choose it again. It is based on the observation that humans tend to prefer playing one type of move over the others.

### Rotation

This strategy predicts the opponent's next move by assuming that they will choose their next move by rotating their last move to the right. For example, if their last move is rock, the next move will be paper. If their last move is paper, the next move will be scissors.

### Reverse Rotation

This strategy only differs from the previous one in the direction of rotation. This strategy predicts the opponent's next move by assuming that they will choose their next move by rotating their last move to the left. For example, if their last move is paper, the next move will be rock. If their last move is scissors, the next move will be paper.

### History Matching

This strategy predicts the opponent's next move by finding repeating patterns in the history. For example, if the last 3 moves the opponent made are rock, paper and scissors, then we find the location in the past where these 3 moves occurred. This strategy assumes that the opponent will play the move after that location.

## Meta-strategies

If we play the above strategies naively, our opponent can easily anticipate it and acts accordingly. The most clever aspect of Iocaine Power is the use of meta-strategies to defeat second-guessing, triple-guessing... Nickel follows the same approach. However, some modifications are made to cater the rules of rock-paper-scissors-lizard-spock. In Nickel, each primary strategy is expanded to ten variants.

* **P0**: Play the primary strategy naively
* **P1**: Assume the opponent knows we will use P0. If P0 predicts rock, they will know that our next move will be paper or spock so they will play lizard. Therefore we should play rock or scissors instead.
* **P2**: Assume the opponent knows we will use P1. If P1 predicts rock, they will know that our next move will be paper or spock so they will play lizard. Therefore we should play rock or scissors instead.
* **P3**: Assume the opponent knows we will use P2. If P2 predicts rock, they will know that our next move will be paper or spock so they will play lizard. Therefore we should play rock or scissors instead.
* **P4**: Assume the opponent knows we will use P3. If P3 predicts rock, they will know that our next move will be paper or spock so they will play lizard. Therefore we should play rock or scissors instead.
* **P'0**: Exchange the position of us and our opponent. If the strategy predicts that we will play rock, the opponent will play paper or spock so we should play lizard instead.
* **P'1 to P'4**: Same as P1 to P4.

## Strategy Selection

Since we have so many strategies to choose from, we need a way to figure out which strategy to use. Nickel makes its decision based on the past performance. If a strategy predicted correctly, its score is incremented. Otherwise, its score is reset to zero. Resetting the score to zero instead of decrementing it allows faster response when the opponent changes their strategy.

## Benchmark

To find out Nickel's performance, I let it to play against the top RSPVL bots in [this tournament](http://codegolf.stackexchange.com/questions/35079/the-rock-paper-scissors-lizard-spock-tournament-of-epicness). Each game consists of 300 rounds. The winner is the one with more wins.

To run the test:

```
mvn package && cd benchmark && ./test.sh
```

Make sure that your Bash version is 4.0 or above.

The following is the output of a particular run:

```
Nickel vs Dienstag.py ..............................
Nickel Wins: 273  Nickel Losses: 17  Ties:  10

Nickel vs TobiasFuenke.py ..............................
Nickel Wins: 107  Nickel Losses: 127  Ties:  66

Nickel vs Pony.py ..............................
Nickel Wins: 144  Nickel Losses: 115  Ties:  41

Nickel vs SuperMarkov.py ..............................
Nickel Wins: 143  Nickel Losses: 108  Ties:  49

Nickel vs BayesianBot.pl ..............................
Nickel Wins: 127  Nickel Losses: 122  Ties:  51

Nickel vs SmarterBot ..............................
Nickel Wins: 102  Nickel Losses: 136  Ties:  62

Nickel vs Gazzr ..............................
Nickel Wins: 120  Nickel Losses: 130  Ties:  50

Nickel vs Alternator.rb ..............................
Nickel Wins: 125  Nickel Losses: 111  Ties:  64

Nickel vs Markov.rb ..............................
Nickel Wins: 138  Nickel Losses: 107  Ties:  55
```

Nickel wins 6 times and loses 3 times. Its performance is pretty good.

## Copyright and License

Code released under the MIT license.

## See Also

* [Iocaine Powder](http://www.ofb.net/~egnor/iocaine.html)
* [The First International RoShamBo Programming Competition](https://webdocs.cs.ualberta.ca/~darse/rsb-results1.html)
* [Rock Paper Scissors Algorithms](http://frc.ri.cmu.edu/~daniellu/programming/rps/)
* [The Rock, Paper, Scissors, Lizard, Spock Tournament of Epicness](http://codegolf.stackexchange.com/questions/35079/the-rock-paper-scissors-lizard-spock-tournament-of-epicness)
