public class Empresa {

    private Integer id;
    private String nome;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

}
public class Banco {

    private static List<Empresa> lista = new ArrayList<>();

    static {
        Empresa empresa = new Empresa();
        empresa.setNome("Alura");
        Empresa empresa2 = new Empresa();
        empresa2.setNome("Caelum");
        Banco.lista.add(empresa);
        Banco.lista.add(empresa2);
    }

    public void adiciona(Empresa empresa) {
        Banco.lista.add(empresa);
    }

    public List<Empresa> getEmpresas() {
        return Banco.lista;
    }
}
@WebServlet("/listaEmpresas")
public class ListaEmpresasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        Banco banco = new Banco();
        List<Empresa> lista = banco.getEmpresas();

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<ul>");

        for (Empresa empresa : lista) {
            out.println("<li> " + empresa.getNome() + "</li>");
        }

        out.println("</ul>");
        out.println("</body></html>");
    }
}
@WebServlet("/novaEmpresa")
public class NovaEmpresaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        System.out.println("Cadastrando nova empresa");

        String nomeEmpresa = request.getParameter("nome");
        Empresa empresa = new Empresa();
        empresa.setNome(nomeEmpresa);

        Banco banco = new Banco();
        banco.adiciona(empresa);

        PrintWriter out = response.getWriter();
        out.println("<html><body>Empresa " + nomeEmpresa + " cadastrada com sucesso!</body></html>");
    }
}
