import {useEffect, useState} from "react";
import {WsrefFormComponent} from "./wsref-form.component";
import {WsrefFilterComponent} from "./wsref-filter.component";
import {WsrefInfoComponent} from "./wsref-info.component";
import {ApiManager} from "../util/api.manager";
import {RoleNames} from "../util/role-names";

export function WsrefListComponent({role}: {
    role: RoleNames
}) {
    let [wsrefList, setWsrefList] = useState<any[]>();
    let [inserting, setInserting] = useState(false);
    let [filtering, setFiltering] = useState(false);
    let [filter, setFilter] = useState("");
    const loadWsrefs = async () => {
        let wsrefsData = await ApiManager.get(`/wsref`, {
            filter: filter?.length > 0 ? filter : undefined
        });
        if (wsrefsData)
            setWsrefList(wsrefsData);
    }
    const insertWsref = async (wsrefDto: any) => {
        await ApiManager.post(`/wsref`, wsrefDto);
        loadWsrefs();
    }
    const updateWsref = async (id: number, wsrefDto: any) => {
        await ApiManager.put(`/wsref/${id}`, wsrefDto);
        loadWsrefs();
    }
    const deleteWsref = async (id: number) => {
        await ApiManager.delete(`/wsref/${id}`);
        loadWsrefs();
    }
    const incrementWsref = async (id: number) => {
        await ApiManager.put(`/wsref/${id}/plus`);
        loadWsrefs();
    }
    const decrementWsref = async (id: number) => {
        await ApiManager.put(`/wsref/${id}/minus`);
        loadWsrefs();
    }
    useEffect(() => {
        loadWsrefs();
    }, []);
    return (
        <div>
            <div>
                {role === RoleNames.ADMIN ? <button onClick={(e: any) => setInserting(true)}>
                    insert
                </button> : null}
                <button onClick={(e: any) => setFiltering(true)}>filter</button>
            </div>
            {inserting ? <WsrefFormComponent insertWsref={async (wsrefDto: any) => {
                await insertWsref(wsrefDto);
                setInserting(false);
            }} onClose={() => setInserting(false)}/> : null}
            {filtering ? <WsrefFilterComponent filter={filter} setFilter={setFilter} submit={async () => {
                await loadWsrefs();
                setFiltering(false);
            }} onClose={() => setFiltering(false)}/> : null}
            {wsrefList?.map((wsref: any) => <WsrefInfoComponent key={wsref.id}
                                                                role={role}
                                                                wsref={wsref}
                                                                updateWsref={updateWsref}
                                                                deleteWsref={deleteWsref}
                                                                incrementWsref={incrementWsref}
                                                                decrementWsref={decrementWsref}/>)}
        </div>
    );
}