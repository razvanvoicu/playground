#include <cstdio>
#include <algorithm>
#include <map>
using namespace std;
const int MAXSERVER = 8000, MAXADMIN = 100, MAXLINK = 1e5;
struct node {
    node *prx, *lft, *rgt;
    bool rev;
    int sum, fx, size;
    node() {
        clear();
    }

    void clear() {
        lft = rgt = prx = NULL;
        rev = 0;
        sum = fx = 0;
        size = 1;
    }

    bool is_root() {
        return !prx || (prx->lft != this && prx->rgt != this);
    }
};

struct lnk {
    node *a, *b;
    lnk() {
        clear();
    }
    node *get() {
        if (a) return a;
        else if (b) return b;
        return NULL;
    }
    node *get_diff(node* x) {
        if (a == x) return b;
        else if (b == x) return a;
        return get();
    }
    void add(node *x) {
        if (!a) a = x;
        else if (!b) b = x;
    }

    void del(node* x) {
        if (a == x) a = NULL;
        else if (b == x) b = NULL;
    }
    void clear() {
        a = b = NULL;
    }
    int count() {
        return (a != NULL) + (b != NULL);
    }
};
typedef pair<int, int> pii;
typedef pair<int, node*> pin;


static void clean_reverse(node *px) {
    if (px->rev) {
        px->rev = 0;
        swap(px->lft, px->rgt);
        if (px->lft)
            px->lft->rev ^= 1;
        if (px->rgt)
            px->rgt->rev ^= 1;
    }
}

static void fix_size_and_sum(node *px) {
    int lsize = px->lft ? px->lft->size : 0;
    int rsize = px->rgt ? px->rgt->size : 0;
    px->size = lsize + rsize + 1;

    int lsum = px->lft ? px->lft->sum : 0;
    int rsum = px->rgt ? px->rgt->sum : 0;
    px->sum = lsum + rsum + px->fx;
}

void zig(node *p) {
    node *q = p->prx, *r = q->prx;
    if ((q->lft = p->rgt)) q->lft->prx = q;
    p->rgt = q, q->prx = p;
    if (((p->prx = r))) {
        if (r->lft == q) r->lft = p;
        if (r->rgt == q) r->rgt = p;
    }
}

void zag(node *p) {
    node *q = p->prx, *r = q->prx;
    if ((q->rgt = p->lft)) q->rgt->prx = q;
    p->lft = q, q->prx = p;
    if (((p->prx = r))) {
        if (r->lft == q) r->lft = p;
        if (r->rgt == q) r->rgt = p;
    }
}
lnk table[MAXSERVER][MAXADMIN];
map<pii, pin> ehash;

static void splay(node *px) {
    struct node *f, *gf;
    while (!px->is_root()) {
        f = px->prx;
        gf = f->prx;
        if (!gf) {
            clean_reverse(f);
            clean_reverse(px);
            if (f->lft == px) zig(px);
            else zag(px);
            fix_size_and_sum(f);
            fix_size_and_sum(px);
        } else {
            clean_reverse(gf);
            clean_reverse(f);
            clean_reverse(px);
            if (gf->lft == f)
                if (f->lft == px) zig(f), zig(px);
                else zag(px), zig(px);
            else if (f->lft == px) zig(px), zag(px);
            else zag(f), zag(px);
            fix_size_and_sum(gf);
            fix_size_and_sum(f);
            fix_size_and_sum(px);
        }
    }
    clean_reverse(px);
}
static void make_last (node *x) {
  splay(x);
  if (x->rgt) x->rev ^= 1;
}

static void make_first (node *x) {
  splay(x);
  if (x->lft) x->rev ^= 1;
}

static void add_edge(int a, int b, int i, node *px) {
    node *l, *r;
    l = table[a][i].get();
    if (l) make_last(l);

    r = table[b][i].get();
    if (r) make_first(r);

    px->lft = l;
    if (l) l->prx = px;

    px->rgt = r;
    if (r) r->prx = px;

    px->rev = 0;
    px->prx = NULL;

    fix_size_and_sum(px);

    table[a][i].add(px);
    table[b][i].add(px);
}

static void del_edge(int a, int b, int x, node *px) {
    splay(px);
    if (px->lft) px->lft->prx = NULL;
    if (px->rgt) px->rgt->prx = NULL;
    table[a][x].del(px);
    table[b][x].del(px);
    px->lft = NULL;
    px->rgt = NULL;
    px->prx = NULL;
    fix_size_and_sum(px);
}

static bool connected(int a, int b, int x) {
    node *pa = table[a][x].get(), *pb = table[b][x].get();
    if (pa != pb) {
        splay(pa), splay(pb);
        return pa->prx != NULL;
    }
    return true;
}

int xrank(node* px) {
    splay(px);
    return px->lft ? px->lft->size : 0;
}

node* frx[MAXLINK];
int main() {
    register int N, M, C, T, i, j, a, b, x, cmd;
    scanf("%d%d%d%d", &N, &M, &C, &T);
    for (i = 0; i < M; ++i)
        frx[i] = new node();
    ehash.clear();
    pii key;
    for (i = 0; i < M; ++i) {
        scanf("%d%d%d", &a, &b, &j);
        --a, --b, --j;
        key = make_pair(a, b);
        node *ptx = frx[i];
        ehash[key] = make_pair(j + 1, ptx);
        add_edge(a, b, j, ptx);
    }
    node *px, *qx, *tx, *ux;
    int rpx, rqx, rtx, rux;
    while (T--) {
        scanf("%d%d%d%d", &cmd, &a, &b, &i);
        --a, --b;
        if (cmd == 1) {
            --i;
            key = make_pair(a, b);
            pin& data = ehash[key];
            x = data.first;
            if (!x) { //not found in edges storage
                printf("Wrong link\n");
                continue;
            }
            --x;
            if (x == i) { //this cable is already controlled by this admin
                printf("Already controlled link\n");
                continue;
            }
            if (table[a][i].count() == 2 || table[b][i].count() == 2) { //at least one of the serves has 2 cables related the new admin
                printf("Server overload\n");
                continue;
            }
            if (table[a][i].count() > 0 && table[b][i].count() > 0 && connected(a, b, i)) { //it is possible to get from one of them to the other
                printf("Network redundancy\n");
                continue;
            }
            px = data.second; //get the proper node (i.e. the node for edge  a <-> b)

            del_edge(a, b, x, px);

            add_edge(a, b, i, px);

            data.first = i + 1; //update the company owning this cable

            printf("Assignment done\n");
        } else if(cmd == 2) {
            key = make_pair(a, b);

            pin& data = ehash[key];

            px = data.second;

            splay(px);

            px->fx = i;

            fix_size_and_sum(px);
        } else {
            --i;
            if (a > b) swap(a, b);
            key = make_pair(a, b);
            pin& data = ehash[key];
            if (data.second && data.first == i + 1) {
                printf("%d security devices placed\n", data.second->fx);
                continue;
            }
            lnk al = table[a][i], bl = table[b][i];
            if (al.count() > 0 && bl.count() > 0 && connected(a, b, i)) {
                if (al.count() == 1 && bl.count() == 1) { //happy case :)
                    px = al.get();
                    make_first(px);
                    x = px->sum;
                } else if (al.count() + bl.count() == 3) { //one is extreme, we need to find the next one
                    if (al.count() > 1) swap(al, bl);

                    px = al.get();
                    make_first(px);
                    x = px->sum;

                    qx = bl.a, tx = bl.b;
                    if (xrank(qx) > xrank(tx)) swap(qx, tx);

                    splay(qx);
                    x -= qx->rgt->sum;
                } else { //hard case, invoking creativity :)
                    px = al.a, qx = al.b, tx = bl.a, ux = bl.b;
                    rpx = xrank(px), rqx = xrank(qx), rtx = xrank(tx), rux = xrank(ux);
                    if (rpx > rqx) swap(px, qx), swap(rpx, rqx);
                    if (rtx > rux) swap(tx, ux), swap(rtx, rux);

                    if (rpx > rtx) swap(px, tx), swap(rpx, rtx), swap(qx, ux), swap(rqx, rux);
                    /**
                     * At this point:
                     * a is to the left and b to the right
                     * px is the lowest a's edge and qx is the highest a's edge
                     * tx is the lowest b's edge and ux is the highest b's edge
                     * */

                    splay(qx);
                    x = qx->fx + qx->rgt->sum;

                    splay(tx);
                    x -= tx->rgt->sum;
                }
                printf("%d security devices placed\n", x);
            } else printf("No connection\n");
        }
    }
    return 0;
}