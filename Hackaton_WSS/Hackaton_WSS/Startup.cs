using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(Hackaton_WSS.Startup))]
namespace Hackaton_WSS
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
