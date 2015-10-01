/**
 * 
 */
package graph;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import recursion.Heap;
import tree.TreeNode;


/**
 * @author Giuseppe Fusco (giuseppe.fusco.666@gmail.com)
 *
 */
public class GraphSearch {

	private static int time;

	public static <V, E> TreeNode<V> breadth_first_search(
			Graph<V, E> graph, Vertex<V> s) throws Exception {
		Iterator<Vertex<V>> it = graph.vertices();
		while(it.hasNext()) 
			it.next().init();
		List<Vertex<V>> queue = new ArrayList<Vertex<V>>();
		List<TreeNode<V>> tree = new ArrayList<TreeNode<V>>();
		TreeNode<V> root = new TreeNode<V>(s.getInfo(), null);
		queue.add(s);
		tree.add(root);
		while(!queue.isEmpty()){
			Vertex<V> u = queue.get(0);
			TreeNode<V> son = tree.get(0);
			queue.remove(0);
			tree.remove(0);
			Iterator<Edge<E, V>> adj = graph.outComingEdges(u);
			while(adj.hasNext()){
				Edge<E, V> eg = adj.next();
				eg.setType(Edge.CROSS);
				Vertex<V> v = (Vertex<V>) eg.getDestination();
				if(v.getColor() == Vertex.WHITE){
					v.setColor(Vertex.GREY);
					v.setDistance(u.getDistance()+1);
					queue.add(v);
					eg.setType(Edge.TREE);
					tree.add(son.addSon(v.getInfo()));
				}
			}
			u.setColor(Vertex.BLACK);
		}

		return root;
	}

	public static <V, E> List<TreeNode<V>> depth_first_search(Graph<V, E> graph, Vertex<V> ver) throws Exception {
		time = 0;
		List<TreeNode<V>> table = new ArrayList<TreeNode<V>>();
		Iterator<Vertex<V>> it = graph.vertices();
		while(it.hasNext())
			it.next().init();
		if(ver==null){
			it = graph.vertices();
			while(it.hasNext())
			{
				Vertex<V> u = it.next();
				if(u.getColor() == Vertex.WHITE){
					TreeNode<V> node = new TreeNode<V>(u.getInfo(), null);
					depth_first_visit(u, graph, node);
					table.add(node);
				}
			}
		}else
		{
			TreeNode<V> node = new TreeNode<V>(ver.getInfo(), null);
			depth_first_visit(ver, graph, node);
			table.add(node);
		}

		return table;
	}

	private static <V, E> void depth_first_visit(Vertex<V> u, 
			Graph<V, E> graph, TreeNode<V> parent) throws Exception {
		u.setColor(Vertex.GREY);
		time++;
		u.setDistance(time);
		Iterator<Edge<E, V>> adj = graph.outComingEdges(u);
		while(adj.hasNext()){
			Edge<E, V> eg = adj.next();
			Vertex<V> v = (Vertex<V>) eg.getDestination();
			if(v.getColor() == Vertex.WHITE){
				eg.setType(Edge.TREE);
				depth_first_visit(v, graph, parent.addSon(v.getInfo()));
			}
			else if(v.getColor() == Vertex.GREY)
				eg.setType(Edge.BACK);
			else if(u.getDistance()>v.getDistance() && v.getColor() == Vertex.BLACK)
				eg.setType(Edge.CROSS);
			else eg.setType(Edge.FORWARD);
		}
		u.setColor(Vertex.BLACK);
		time++;
		u.setFinishingTime(time);
	}

	public static <V, E> List<Vertex<V>> topologicalSort(Graph<V, E> graph) throws Exception {
		List<Vertex<V>> sort = new ArrayList<Vertex<V>>();
		depth_first_search(graph, null);
		Iterator<Vertex<V>> it = graph.vertices();
		while(it.hasNext())
			sort.add(0, it.next());

		return sort;
	}

	public static <V, E> List<TreeNode<V>> stronglyConnectedComponents(Graph<V, E> graph) throws Exception{
		List<TreeNode<V>> table = new ArrayList<TreeNode<V>>();
		List<Vertex<V>> sort = new ArrayList<Vertex<V>>();
		depth_first_search(graph, null);
		Iterator<Vertex<V>> it = graph.vertices();
		while(it.hasNext())
			sort.add(it.next());
		graph.reverseDirection();
		Collections.sort(sort, new Comparator<Vertex<V>>() {
			@Override
			public int compare(Vertex<V> o1, Vertex<V> o2) {
				return o1.getFinishingTime()-o2.getFinishingTime();
			}
		});
		Collections.reverse(sort);
		it = graph.vertices();
		while(it.hasNext())
			it.next().init();
		for(int i=0; i<sort.size(); i++)
		{
			Vertex<V> v = sort.get(i);
			if(v.getColor() == Vertex.WHITE)
				table.add(depth_first_search(graph, v).get(0));
		}
		graph.reverseDirection();
		return table;
	}

	public static <V, E> void tarjan(Graph<V, E> graph) throws Exception{
		time = 0;
		List<Vertex<V>> stack = new ArrayList<Vertex<V>>();
		Iterator<Vertex<V>> it = graph.vertices();
		while(it.hasNext())
		{
			Vertex<V> v = it.next();
			v.init();
			v.setColor(-1);
		}
		it = graph.vertices();
		while(it.hasNext())
		{
			Vertex<V> v = it.next();
			if(v.getColor()==-1)
				tarjan(graph, v, stack);
		}

	}

	private static <V, E> void tarjan(Graph<V, E> graph, Vertex<V> v, List<Vertex<V>> stack) throws Exception{
		v.setColor(time);
		v.setDistance(time);
		time++;
		stack.add(0, v);
		Iterator<Vertex<V>> it = graph.adjacentVertices(v);
		while(it.hasNext()){
			Vertex<V> u = it.next();
			if(u.getColor()==-1){
				tarjan(graph, u, stack);
				v.setDistance(Math.min(v.getDistance(), u.getDistance()));
			}
			else if(stack.contains(u)){
				v.setDistance(Math.min(v.getDistance(), u.getColor()));
			}
		}

		if(v.getDistance()==v.getColor()){
			if(stack.size()>0){
				Vertex<V> v_ = stack.get(0);
				System.out.println("SSC");
				while(v!=null){
					stack.remove(0);
					System.out.println("\t"+v_.getInfo());
					try {
						v_ = stack.get(0);
					} catch (Exception e) {
						v = null;
					}
				}
				System.out.println();
			}
		}
	}

	public static <V, E> List<Vertex<V>> minimumSpanningTree_Prim(
			Graph<V, E> graph, Vertex<V> s) throws Exception{
		List<Vertex<V>> list = new ArrayList<Vertex<V>>();
		Heap<Vertex<V>> heap = new Heap<Vertex<V>>();
		Comparator<Vertex<V>> comp = new Comparator<Vertex<V>>() {
			@Override
			public int compare(Vertex<V> arg0, Vertex<V> arg1) {
				return Double.compare(arg0.getKey(), arg1.getKey());
			}
		};
		Iterator<Vertex<V>> it = graph.vertices();
		while(it.hasNext()){
			Vertex<V> v = it.next();
			v.init();
			v.setKey(Double.MAX_VALUE);
			heap.insert(v, comp);
		}
		s.setKey(0);
		heap.heapSort(comp);

		while(!heap.isEmpty()){
			Vertex<V> u = heap.extractMin(comp);
			u.setColor(Vertex.BLACK);
			list.add(u);
			Iterator<Edge<E, V>> adj = graph.outComingEdges(u);
			while(adj.hasNext()){
				Edge<E, V> eg = adj.next();
				Vertex<V> v = (Vertex<V>) eg.getDestination();
				if(v.getColor()==Vertex.WHITE && eg.getWeight()<v.getKey()){
					v.setKey(eg.getWeight());
					heap.heapSort(comp);
				}
			}
		}
		
		return list;
	}
	
	public static <V, E> boolean bellmanFord(Graph<V, E> graph, Vertex<V> s){
		Iterator<Vertex<V>> it = graph.vertices();
		while(it.hasNext()){
			Vertex<V> v = it.next();
			v.init();
			v.setKey(Double.MAX_VALUE);
		}
		s.setKey(0);
		for(int i=0; i<graph.numOfVertices()-1; i++){
			Iterator<Edge<E, V>> ed_it = graph.edges();
			while(ed_it.hasNext()){
				Edge<E, V> ed = ed_it.next();
				Vertex<V> u = ed.getSource();
				Vertex<V> v = ed.getDestination();
				if(v.getKey()>u.getKey()+ed.getWeight())
					v.setKey(u.getKey()+ed.getWeight());
			}
		}
		Iterator<Edge<E, V>> ed_it = graph.edges();
		while(ed_it.hasNext()){
			Edge<E, V> ed = ed_it.next();
			Vertex<V> u = ed.getSource();
			Vertex<V> v = ed.getDestination();
			if(v.getKey()>u.getKey()+ed.getWeight())
				return false;
		}
		return true;
	}
	
	public static <V, E> List<Edge<E, V>> dijkstra(Graph<V, E> graph, Vertex<V> s) throws Exception{
		List<Vertex<V>> queue = new ArrayList<Vertex<V>>();
		List<Edge<E, V>> edges = new ArrayList<Edge<E,V>>();
		Heap<Vertex<V>> heap = new Heap<Vertex<V>>();
		Comparator<Vertex<V>> comp = new Comparator<Vertex<V>>() {
			@Override
			public int compare(Vertex<V> arg0, Vertex<V> arg1) {
				return Double.compare(arg0.getKey(), arg1.getKey());
			}
		};
		Iterator<Vertex<V>> it = graph.vertices();
		while(it.hasNext()){
			Vertex<V> v = it.next();
			v.init();
			v.setKey(Double.MAX_VALUE);
			heap.insert(v, comp);
		}
		s.setKey(0);
		heap.heapSort(comp);
		while(!heap.isEmpty()){
			Vertex<V> u = heap.extractMin(comp);
			queue.add(u);
			Iterator<Edge<E, V>> ed_it = graph.outComingEdges(u);
			while(ed_it.hasNext()){
				Edge<E, V> ed = ed_it.next();
				Vertex<V> v = ed.getDestination();
				if(v.getKey()>u.getKey()+ed.getWeight())
					{
					v.setKey(u.getKey()+ed.getWeight());
					edges.add(ed);
					heap.heapSort(comp);
					}
			}
		}
		return edges;
	}
	
	public static <V, E> List<Vertex<V>> dagShortestPaths(Graph<V, E> graph, Vertex<V> s) throws Exception{
		List<Vertex<V>> queue = new ArrayList<Vertex<V>>();
		List<Vertex<V>> list = topologicalSort(graph);
		for(int i=0; i<list.size(); i++){
			list.get(i).setKey(Double.MAX_VALUE);
		}
		s.setKey(0);
		for(int i=0; i<list.size(); i++){
			Vertex<V> u = list.get(i);
			queue.add(u);
			Iterator<Edge<E, V>> ed_it = graph.outComingEdges(u);
			while(ed_it.hasNext()){
				Edge<E, V> ed = ed_it.next();
				Vertex<V> v = ed.getDestination();
				if(v.getKey()>u.getKey()+ed.getWeight())
					v.setKey(u.getKey()+ed.getWeight());
			}
		}
		
		return queue;
	}

	public static void main(String[] args) throws Exception{
		Graph<String, String> g = new GraphEdgeListStructure<String, String>();
		GraphReader.read(new File("src/graph/graph_5.txt"), g, false, true);
		System.out.println(GraphSearch.minimumSpanningTree_Prim(g, g.getVertex("g")));
		
	}

}
