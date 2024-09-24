# Advanced Tic Tac Toe AI: An Exploration of Adversarial Search Algorithms

## Project Overview
This project implements a state-of-the-art Tic Tac Toe AI system, showcasing advanced adversarial search algorithms and heuristic evaluation techniques. It extends beyond the traditional 3x3 grid, offering a scalable solution for larger board sizes and variable winning conditions, thus presenting a more complex challenge in the domain of game theory and artificial intelligence.

## Key Features
- Scalable board dimensions: Supports 3x3 and larger grids
- Configurable winning conditions: Adaptable to various game rules
- Sophisticated AI algorithm: Utilizes enhanced Minimax with Alpha-Beta pruning
- Online multiplayer capability: Integrates with a remote game server via API

## Technical Specifications
- Language: Java
- Core Algorithms:
  1. Enhanced Minimax algorithm
  2. Alpha-Beta pruning for search optimization
  3. Depth-limited search (depth = 4) for performance balancing
  4. Custom heuristic evaluation function based on potential winning sequences

## Algorithmic Innovations
### 1. Enhanced Minimax with Alpha-Beta Pruning
The core of our AI employs a modified Minimax algorithm, crucial for navigating the game's decision tree. By incorporating Alpha-Beta pruning, we significantly reduce the search space, allowing for deeper exploration within reasonable time constraints. This optimization is particularly vital for larger board sizes where the branching factor increases exponentially.

### 2. Depth-Limited Search
To manage the computational complexity, especially in expanded game scenarios, we implement a depth-limited search. Set at a depth of 4, this approach strikes a balance between decision quality and processing speed, enabling the AI to make informed decisions even in the early game stages of larger boards.

### 3. Advanced Heuristic Evaluation
Our custom heuristic function evaluates board states based on the longest potential winning sequences for both players. This nuanced approach allows the AI to prioritize moves that simultaneously maximize its winning potential while minimizing the opponent's opportunities, a critical strategy in extended Tic Tac Toe variants.

### 4. Dynamic Algorithm Adaptation
The system dynamically adjusts its algorithmic approach based on the current game state and board size, ensuring optimal performance across various game configurations. This adaptive strategy showcases the AI's ability to handle diverse game scenarios efficiently.

## Implementation Details
- `Logic.java`: Encapsulates the game's core logic and AI decision-making processes
- `Main.java`: Manages game initialization and user interaction
- `Requests.java`: Handles HTTP communication with the game server, enabling online play

## Academic Significance
This project demonstrates the practical application of advanced AI concepts in game theory:
1. It showcases the scalability of traditional game AI algorithms to more complex scenarios.
2. The implementation of Alpha-Beta pruning highlights the importance of search optimization in AI.
3. The custom heuristic function exemplifies the critical role of domain-specific knowledge in AI systems.
4. The online integration aspect addresses real-world considerations in AI deployment.

## Future Research Directions
- Integration of machine learning models for improved heuristic evaluation
- Exploration of Monte Carlo Tree Search (MCTS) as an alternative to Minimax
- Implementation of reinforcement learning techniques for adaptive gameplay
- Analysis of AI performance across various board sizes and winning conditions



这个项目是一个高级的井字棋（Tic Tac Toe）游戏AI实现。它不仅能够玩标准的3x3井字棋，还可以处理更大尺寸的棋盘和更长的连线要求。以下是对这个项目的概述：

项目目的：
开发一个智能的井字棋AI，能够在不同大小的棋盘上与人类或其他AI对弈。
核心算法：

Minimax算法的变体
Alpha-Beta剪枝
深度限制搜索（深度为4）
启发式评估函数


主要特点：

可变棋盘大小：不限于传统的3x3棋盘
可调整获胜条件：连线数量可配置
与在线对战系统集成：通过API与远程服务器通信


关键组件：

Logic.java：包含游戏的核心逻辑和AI决策算法
Main.java：处理游戏初始化和用户交互
Requests.java：管理与远程服务器的HTTP通信


AI策略：

使用Minimax算法评估移动
应用Alpha-Beta剪枝优化搜索效率
深度限制为4，平衡计算速度和准确性
启发式函数基于最长连线评估局面


在线功能：

创建新游戏
加入现有游戏
与远程对手对战

# Advanced Tic Tac Toe AI

## 项目概述
这是一个基于Java实现的高级井字棋（Tic Tac Toe）AI系统。该项目不仅支持标准的3x3棋盘，还可以在更大尺寸的棋盘上进行游戏，并支持自定义获胜条件。AI采用了改进的Minimax算法，结合Alpha-Beta剪枝和深度限制搜索，以实现高效的决策过程。

## 主要特性
- 可变棋盘尺寸：支持3x3及更大尺寸的棋盘
- 灵活的获胜条件：可自定义连线数量
- 高效AI算法：使用Minimax算法、Alpha-Beta剪枝和深度限制搜索
- 在线对战功能：通过API与远程服务器通信，支持创建和加入在线游戏

## 技术细节
- 编程语言：Java
- 核心算法：
  - Minimax算法的变体
  - Alpha-Beta剪枝
  - 深度限制搜索（深度为4）
  - 基于最长连线的启发式评估函数
- 网络通信：使用HTTP请求与游戏服务器交互

## 项目结构
- `Logic.java`: 包含游戏核心逻辑和AI决策算法
- `Main.java`: 处理游戏初始化和用户交互
- `Requests.java`: 管理与远程服务器的HTTP通信

## 如何使用
1. 确保您的系统已安装Java运行环境。
2. 克隆或下载本项目到本地。
3. 编译项目：

4. 运行主程序：
5. 按照提示输入游戏参数（棋盘大小、目标连线数等）。
6. 选择创建新游戏或加入现有游戏。

## AI策略
AI使用改进的Minimax算法来评估每一步可能的移动。通过Alpha-Beta剪枝优化搜索效率，并使用深度限制（深度为4）来平衡计算速度和决策质量。启发式函数基于当前局面中最长的潜在连线来评估位置优劣。

## 在线功能
- 创建新游戏：设置游戏参数并等待对手加入
- 加入现有游戏：输入游戏ID来加入其他玩家创建的游戏
- 实时对战：通过API与远程对手进行实时对战

## 未来改进方向
- 实现图形用户界面（GUI）
- 优化AI算法以支持更大尺寸的棋盘
- 添加机器学习模型以提高AI性能
- 实现本地多人模式

## 贡献
欢迎贡献代码或提出改进建议。请fork本项目，创建您的特性分支，提交变更，并发起pull request。
