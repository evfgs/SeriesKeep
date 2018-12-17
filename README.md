# Series Keep

Proposta de Projeto: 
	
	Um aplicativo onde o usuário pode marcar quais séries de televisão está
	acompanhando no momento(episódios e temporadas) e receber notificações sobre
	lançamentos de novos episódios e notícias sobre estas séries. O usuário também pode
	marcar filmes que tem interesse e serão lançados nos próximos meses e receber notícias
	sobre os mesmos.
	Dados obtidos de: https://www.themoviedb.org/
	
	
	
Projeto Entregue:
	
	App onde o Usuário pode consultar descrição e avaliação de séries 
	buscando pelo titulo da mesma
	
	

Tecnologias utilizadas:

	Gson:
		O Gson é uma biblioteca Java que pode ser usada para converter objetos Java em sua representação JSON. Também pode ser usado para converter uma string JSON em um objeto Java equivalente. O Gson pode trabalhar com objetos Java arbitrários, incluindo objetos pré-existentes dos quais você não possui código-fonte.
		
	Anko:
		Utilizada para rodar tarefas assíncronas para carregar a lista de séries e não deixar o usuário travado numa tela enquanto a aplicação espera o processamento da requisição
		
	Dagger 2:
	
		É uma abordagem em tempo de compilação para injeção de dependências
		
	Retrofit:
	
		Para lidar com a parte de requisições HTTP foi consumida uma API REST utilizando Retrofit, pois a parte de configuração é bem mais simples(configuramos apenas o necessário para estabelecer a comunicação), a questão de serialização dos objetos(não precisamos fazer essa tarefa manualmente) e a usabilidade(é bem mais fácil realizar requisições com o Retrofit).
		
	Picasso:
	
		Utilizado para baixar, processar e configurar as imagens dentro da aplicação
		
		
		
	Dupla: Emanuel Victor
		   Edymir Etienne




	
	

